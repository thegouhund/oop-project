package com.project.controller;

import com.project.api.ScheduleMockApi;
import com.project.dao.AirportDAO;
import com.project.dao.PassengerDAO;
import com.project.dao.ScheduleDAO;
import com.project.dao.TicketDAO;
import com.project.model.Airport;
import com.project.model.Passenger;
import com.project.model.Schedule;
import com.project.components.ScheduleUI;
import com.project.model.Ticket;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.swing.text.LabelView;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.EventObject;

import static javafx.collections.FXCollections.observableArrayList;

public class FormController {
    @FXML
    private VBox vboxMain;
    @FXML
    private MFXFilterComboBox<Airport> cbFrom;
    @FXML
    private MFXFilterComboBox<Airport> cbDestination;
    @FXML
    private MFXDatePicker datePickerDeparture;
    @FXML
    private VBox vboxPassengerField;
    @FXML
    private MFXComboBox<String> choiceSeat;
    @FXML
    private VBox vBoxSchedule;
    @FXML
    private Label labelErrorMsgCbAirportFrom;
    @FXML
    private Label labelErrorMsgCbAirportDestination;
    @FXML
    private Label labelErrorMsgDatePicker;

    Connection connection = DatabaseUtils.getConnection();

    @FXML
    private void initialize() {
        AirportDAO airportDao = new AirportDAO(connection);
        ObservableList<Airport> airportChoices = observableArrayList(airportDao.getAll());
        cbFrom.setItems(airportChoices);
        cbDestination.setItems(airportChoices);

        String[] seatsArray = {"Economy", "Premium", "Business", "First Class"};
        ObservableList<String> seats = observableArrayList(seatsArray);
        choiceSeat.setItems(seats);

        onClickAddPassenger();

        labelErrorMsgCbAirportFrom.setVisible(false);
        labelErrorMsgCbAirportDestination.setVisible(false);
        labelErrorMsgDatePicker.setVisible(false);
    }

    private int getPassengerAmount() {
        int counter = 1;
        for (Object nodes : vboxPassengerField.getChildren()) {
            if (nodes instanceof HBox) {
                counter++;
            }
        }
        return counter;
    }

    private ArrayList<Passenger> getInputtedPassengers() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (Node node : vboxPassengerField.getChildren()) {
            HBox hbox = (HBox) node;
            MFXTextField nameField = (MFXTextField) hbox.getChildren().getFirst();
            MFXTextField ageField = (MFXTextField) hbox.getChildren().getLast();
            passengers.add(new Passenger(nameField.getText(), Integer.parseInt(ageField.getText())));
        }
        return passengers;
    }

    @FXML
    private void onClickAddPassenger() {
        int passengerAmount = getPassengerAmount();

        HBox hbox = new HBox();
        MFXTextField nameField = new MFXTextField();
        MFXTextField ageField = new MFXTextField();
        nameField.setFloatingText("Nama penumpang " + passengerAmount);
//        Label labelErrorMsgNameField = new Label("Masukkan nama penumpang!");
//        hbox.getChildren().add(labelErrorMsgNameField);
//        labelErrorMsgNameField.setVisible(false);
        nameField.setMinWidth(200);
        ageField.setFloatingText("Usia penumpang " + passengerAmount);
        ageField.setMinWidth(200);

        hbox.setSpacing(16);
        hbox.setMinWidth(400);
        hbox.getChildren().addAll(nameField, ageField);
        vboxPassengerField.getChildren().add(hbox);
    }

    private void onTicketBuy(Schedule schedule) {
        PassengerDAO passengerDAO = new PassengerDAO(connection);
        ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
        TicketDAO ticketDAO = new TicketDAO(connection);

        Ticket ticket = new Ticket(schedule);
        for (Passenger passenger : getInputtedPassengers()) {
            passengerDAO.add(passenger);
            ticket.getPassengers().add(passenger);
        }

        scheduleDAO.add(schedule);
        ticketDAO.add(ticket, schedule);
    }

    @FXML
    private void onTicketSearch(ActionEvent event) {
        if (isFormValid()) {
            Schedule schedule = ScheduleMockApi.generate(cbFrom.getValue(), cbDestination.getValue(), datePickerDeparture.getValue().toString());
            ScheduleUI scheduleUI = new ScheduleUI(schedule);
            scheduleUI.setBuyButtonAction(e -> onTicketBuy(schedule));

            vBoxSchedule.getChildren().add(scheduleUI);
        }
    }

    @FXML
    private boolean isFormValid() {
        int checks = 0;
        if (cbFrom.getValue() == null) {
            cbFrom.setStyle("-fx-border-color: red;-mfx-color: red;");
            labelErrorMsgCbAirportFrom.setVisible(true);
        } else {
            checks++;
        }
        if (cbDestination.getValue() == null) {
            cbDestination.setStyle("-fx-border-color: red;-mfx-color: red;");
            labelErrorMsgCbAirportDestination.setVisible(true);
        } else {
            checks++;
        }
        if (datePickerDeparture.getValue() == null) {
            datePickerDeparture.setStyle("-fx-border-color: red;-mfx-color: red;");
            labelErrorMsgDatePicker.setVisible(true);
        } else {
            checks++;
        }

        int loopCounter = 0;
        for (Node node : vboxPassengerField.getChildren()) {
            HBox hbox = (HBox) node;
            MFXTextField nameField = (MFXTextField) hbox.getChildren().getFirst();
            MFXTextField ageField = (MFXTextField) hbox.getChildren().getLast();
            if (nameField.getText().isEmpty()) {
                nameField.setStyle("-fx-border-color: red;-mfx-color: red;");
            } else {
                checks++;
            }

            if (ageField.getText().isEmpty()) {
                ageField.setStyle("-fx-border-color: red;-mfx-color: red;");
            } else {
                checks++;
            }

            loopCounter += 2;
        }

        System.out.println(checks);
        System.out.println(3 + loopCounter);
        return checks == 3 + loopCounter;
    }
}
