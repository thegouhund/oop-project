package com.project.controller;

import com.project.dao.AirportDAO;
import com.project.dao.PassengerDAO;
import com.project.dao.ScheduleDAO;
import com.project.dao.TicketDAO;
import com.project.factory.ScheduleFactory;
import com.project.model.Airport;
import com.project.model.Passenger;
import com.project.model.Schedule;
import com.project.components.ScheduleUI;
import com.project.model.Ticket;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class FormController {
    @FXML
    private VBox vboxMain;
    @FXML
    private TextField fieldName;
    @FXML
    private MFXFilterComboBox<Airport> cbFrom;
    @FXML
    private MFXFilterComboBox<Airport> cbDestination;
    @FXML
    private MFXDatePicker datePickerDeparture;
    @FXML
    private ImageView iconFrom;
    @FXML
    private ImageView iconDestination;
    @FXML
    private VBox vboxPassengerField;
    @FXML
    private MFXComboBox<String> choiceSeat;
    @FXML
    private VBox vBoxSchedule;

    Connection connection = DatabaseUtils.getConnection();

    @FXML
    private void initialize() {

        AirportDAO airportDao = new AirportDAO(connection);
        ObservableList<Airport> airportChoices = FXCollections.observableArrayList(airportDao.getAll());
        cbFrom.setItems(airportChoices);
        cbDestination.setItems(airportChoices);

        String[] seatsArray = {"Economy", "Premium", "Business", "First Class"};
        ObservableList<String> seats = observableArrayList(seatsArray);
        choiceSeat.setItems(seats);

        onClickAddPassenger();
    }

//    private ObservableList<Airport> getAirportsChoice() {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode json = null;
//        try {
//            json = objectMapper.readTree(new File("src/main/resources/com/project/api/airports.json"));
//            ObservableList<Airport> options = observableArrayList();
//            for (int i = 0; i < json.size(); i++) {
//                String city = String.valueOf(json.get(i).get("city")).replace("\"", "");
//                String iata = String.valueOf(json.get(i).get("iata")).replace("\"", "");
//
//                Airport airport = new Airport(iata, city);
//
//                options.add(airport);
//            }
//
//            return options;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @FXML
    private void onClickAddPassenger() {
        int passengerAmount = getPassengerAmount();

        HBox hbox = new HBox();
        MFXTextField nameField = new MFXTextField();

        MFXTextField ageField = new MFXTextField();

        nameField.setFloatingText("Nama penumpang " + passengerAmount);
        nameField.setMinWidth(200);
        ageField.setFloatingText("Usia penumpang " + passengerAmount);
        ageField.setMinWidth(200);

        hbox.setSpacing(16);
        hbox.setMinWidth(400);
        hbox.getChildren().addAll(nameField, ageField);
        vboxPassengerField.getChildren().add(hbox);
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
    private void onSubmit() {
        Schedule schedule = ScheduleFactory.generate(cbFrom.getValue(), cbDestination.getValue(), datePickerDeparture.getValue().toString());
        ScheduleUI scheduleUI = new ScheduleUI(schedule);


        scheduleUI.setBuyButtonAction(e -> onTicketBuy(schedule));

        vBoxSchedule.getChildren().add(scheduleUI);
    }
}
