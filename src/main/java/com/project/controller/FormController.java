package com.project.controller;

import com.project.api.ScheduleMockApi;
import com.project.dao.AirportDAO;
import com.project.dao.PassengerDAO;
import com.project.dao.ScheduleDAO;
import com.project.dao.TicketDAO;
import com.project.model.*;
import com.project.components.ScheduleUI;
import com.project.router.AppRouter;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import io.github.palexdev.mfxresources.fonts.MFXIconWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class FormController extends Controller {
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
    @FXML
    private Label labelTiketPenerbangan;
    @FXML
    private MFXIconWrapper iconAirportArrival;
    @FXML
    private MFXIconWrapper iconAirportDestination;
    @FXML
    private MFXIconWrapper iconDatePickerDeparture;
    @FXML
    private MFXIconWrapper iconCbSeatChoice;

    private User user;

    Connection connection = DatabaseUtils.getConnection();

    public FormController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        iconAirportArrival.setIcon(new MFXFontIcon("fas-plane-departure", 20)); // todo
        iconAirportDestination.setIcon(new MFXFontIcon("fas-plane-arrival", 20));
        iconDatePickerDeparture.setIcon(new MFXFontIcon("fas-calendar", 20));
        iconCbSeatChoice.setIcon(new MFXFontIcon("fas-chair", 20));

        AirportDAO airportDao = new AirportDAO(connection);
        ObservableList<Airport> airportChoices = observableArrayList(airportDao.getAll());
        cbFrom.setItems(airportChoices);
        cbDestination.setItems(airportChoices);

        String[] seatsArray = {"Economy", "Premium", "Business", "First Class"};
        ObservableList<String> seats = observableArrayList(seatsArray);
        choiceSeat.setItems(seats);

        onClickAddPassenger();

        labelTiketPenerbangan.setVisible(false);
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
            MFXTextField nameField = (MFXTextField) hbox.getChildren().get(0);
            MFXTextField ageField = (MFXTextField) hbox.getChildren().get(1);
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
        MFXButton deleteButton = new MFXButton("×" );
        nameField.setFloatingText("Nama penumpang " + passengerAmount);
        nameField.setMinWidth(200);
        ageField.setFloatingText("Usia penumpang " + passengerAmount);
        ageField.setMinWidth(200);

        deleteButton.setOnAction(e -> {
            vboxPassengerField.getChildren().remove(hbox);
        });

        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(16);
        hbox.setMinWidth(400);

        if (vboxPassengerField.getChildren().isEmpty()) {
            hbox.getChildren().addAll(nameField, ageField);
        } else {
            hbox.getChildren().addAll(nameField, ageField, deleteButton);

        }
        vboxPassengerField.getChildren().add(hbox);
    }

    @FXML
    private void onTicketSearch(ActionEvent event) {
        if (isFormValid()) {
            vBoxSchedule.getChildren().clear();
            vBoxSchedule.getChildren().add(labelTiketPenerbangan);
            for (int i = 0; i < 10; i++) {
                Schedule schedule = ScheduleMockApi.generate(cbFrom.getValue(), cbDestination.getValue(), datePickerDeparture.getValue().toString(), getInputtedPassengers().size());
                ScheduleUI scheduleUI = new ScheduleUI(schedule, true);
                scheduleUI.setButtonAction(e -> onTicketBuy(schedule, schedule.getPrice()));

                vBoxSchedule.getChildren().add(scheduleUI);
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void onTicketBuy(Schedule schedule, double price) {
        PassengerDAO passengerDAO = new PassengerDAO(connection);
        ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
        TicketDAO ticketDAO = new TicketDAO(connection);

        Ticket ticket = new Ticket(schedule);
        for (Passenger passenger : getInputtedPassengers()) {
            int passengerId = passengerDAO.addAndGetGeneratedId(passenger);
            passenger.setId(passengerId);
            ticket.getPassengers().add(passenger);
        }
        int scheduleId = scheduleDAO.addAndGetGeneratedId(schedule, price);
        schedule.setId(scheduleId);
        ticketDAO.add(this.user, ticket, schedule);
    }

    @FXML
    private boolean isFormValid() {
        int checks = 0;
        if (cbFrom.getValue() == null) {
            cbFrom.setStyle("-fx-border-color: red;-mfx-color: red;" );
            labelErrorMsgCbAirportFrom.setVisible(true);
        } else {
            checks++;
        }
        if (cbDestination.getValue() == null) {
            cbDestination.setStyle("-fx-border-color: red;-mfx-color: red;" );
            labelErrorMsgCbAirportDestination.setVisible(true);
        } else {
            checks++;
        }
        if (datePickerDeparture.getValue() == null) {
            datePickerDeparture.setStyle("-fx-border-color: red;-mfx-color: red;" );
            labelErrorMsgDatePicker.setVisible(true);
        } else {
            checks++;
        }

        int loopCounter = 0;
        for (Node node : vboxPassengerField.getChildren()) {
            HBox hbox = (HBox) node;
            MFXTextField nameField = (MFXTextField) hbox.getChildren().get(0);
            MFXTextField ageField = (MFXTextField) hbox.getChildren().get(1);
            if (nameField.getText().isEmpty()) {
                nameField.setStyle("-fx-border-color: red;-mfx-color: red;" );
            } else {
                checks++;
            }

            if (ageField.getText().isEmpty() || !ageField.getText().matches("\\d+" )) { // is empty or is not a number
                ageField.setStyle("-fx-border-color: red;-mfx-color: red;" );
            } else {
                checks++;
            }

            loopCounter += 2;
        }

        if (checks == 3 + loopCounter && loopCounter != 0) {
            cbFrom.setStyle("");
            labelErrorMsgCbAirportFrom.setVisible(false);

            cbDestination.setStyle("");
            labelErrorMsgCbAirportDestination.setVisible(false);

            datePickerDeparture.setStyle("");
            labelErrorMsgDatePicker.setVisible(false);

            for (Node node : vboxPassengerField.getChildren()) {
                HBox hbox = (HBox) node;
                MFXTextField nameField = (MFXTextField) hbox.getChildren().get(0);
                MFXTextField ageField = (MFXTextField) hbox.getChildren().get(1);
                nameField.setStyle("");
                ageField.setStyle("");
            }

            labelTiketPenerbangan.setVisible(true);
            return true;
        }
        return false;
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + "UserTicket.fxml" ));
        loader.setControllerFactory(c -> new UserTicketController(user));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
