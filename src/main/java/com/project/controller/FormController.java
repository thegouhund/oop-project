package com.project.controller;

import com.project.factory.ScheduleFactory;
import com.project.model.Airport;
import com.project.model.Schedule;
import com.project.components.ScheduleUI;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import java.io.File;
import java.io.IOException;

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

    @FXML
    private void initialize() {
        cbFrom.setItems(getAirportsChoice());
        cbDestination.setItems(getAirportsChoice());
        String[] seatsArray = {"Economy", "Premium", "Business", "First Class"};
        ObservableList<String> seats = observableArrayList(seatsArray);
        choiceSeat.setItems(seats);

    }

    private ObservableList<Airport> getAirportsChoice() {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = objectMapper.readTree(new File("src/main/resources/com/project/api/airports.json"));
            ObservableList<Airport> options = observableArrayList();
            for (int i = 0; i < json.size(); i++) {
                String city = String.valueOf(json.get(i).get("city")).replace("\"", "");
                String iata = String.valueOf(json.get(i).get("iata")).replace("\"", "");
                String airport_name = String.valueOf(json.get(i).get("airport_name")).replace("\"", "");

                Airport airport = new Airport(airport_name, iata, city);

                options.add(airport);
            }

            return options;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onClickAddPassenger() {
        int passengerAmount = getPassengerAmount();

        HBox hbox = new HBox();
        VBox vboxNameField = new VBox();
        Label labelNameField = new Label("Nama Penumpang " + passengerAmount);
        MFXTextField nameField = new MFXTextField();
        vboxNameField.getChildren().addAll(labelNameField, nameField);
        vboxNameField.setPrefWidth(254);

        VBox vboxAgeField = new VBox();
        Label labelAgeField = new Label("Usia Penumpang " + passengerAmount);
        MFXTextField ageField = new MFXTextField();
        vboxAgeField.getChildren().addAll(labelAgeField, ageField);
        vboxAgeField.setPrefWidth(254);

        nameField.setFloatingText("Nama penumpang");
        nameField.setMaxWidth(Double.MAX_VALUE);
        ageField.setFloatingText("Usia penumpang");
        ageField.setMaxWidth(Double.MAX_VALUE);

        hbox.setSpacing(16);
        hbox.setPrefWidth(Double.MAX_VALUE);
        hbox.getChildren().addAll(vboxNameField, vboxAgeField);
//        vboxPassengerField.getChildren().add(new Label("Nama Penumpang " + passengerAmount));
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

    @FXML
    private void onSubmit() {
        Schedule schedule = ScheduleFactory.generate(cbFrom.getValue(), cbDestination.getValue(), datePickerDeparture.getValue().toString());
        ScheduleUI scheduleUI = new ScheduleUI(schedule);
        vBoxSchedule.getChildren().add(scheduleUI);
    }
}