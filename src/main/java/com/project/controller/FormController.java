package com.project.controller;

import com.project.factory.AirlineFactory;
import com.project.factory.ScheduleFactory;
import com.project.model.Schedule;
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
    private SearchableComboBox<String> cbFrom;
    @FXML
    private SearchableComboBox<String> cbDestination;
    @FXML
    private DatePicker datePickerDeparture;
    @FXML
    private ImageView iconFrom;
    @FXML
    private ImageView iconDestination;
    @FXML
    private VBox vboxPassengerField;
    @FXML
    private ChoiceBox<String> choiceSeat;

    @FXML
    private void initialize() {
        cbFrom.setItems(getAirportsChoice());
        cbDestination.setItems(getAirportsChoice());
        String[] seatsArray = {"Economy", "Premium", "Business", "First Class"};
        ObservableList<String> seats = observableArrayList(seatsArray);
        choiceSeat.setItems(seats);
    }

    private ObservableList<String> getAirportsChoice() {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = objectMapper.readTree(new File("src/main/resources/com/project/api/airports.json"));
            ObservableList<String> options = observableArrayList();
            for (int i = 0; i < json.size(); i++) {
                String city = String.valueOf(json.get(i).get("city")).replace("\"", "");
                String iata = String.valueOf(json.get(i).get("iata")).replace("\"", "");

                options.add(city + " (" + iata + ")");
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
        TextField nameField = new TextField();
        vboxNameField.getChildren().addAll(labelNameField, nameField);
        vboxNameField.setMinWidth(185);

        VBox vboxAgeField = new VBox();
        Label labelAgeField = new Label("Usia Penumpang " + passengerAmount);
        TextField ageField = new TextField();
        vboxAgeField.getChildren().addAll(labelAgeField, ageField);
        vboxAgeField.setMinWidth(185);

        nameField.setPromptText("Nama penumpang");
        nameField.setMinHeight(40);
        ageField.setPromptText("Usia penumpang");
        ageField.setMinHeight(40);

        hbox.setSpacing(16);
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

        System.out.println(AirlineFactory.generate().getPriceMultiplier());
        createScheduleUI();
    }

    private HBox createScheduleUI() {
//        Schedule schedule = new Schedule(AirlineFactory.generate(), strToLocalDateTime("04-04-2024 23:00:00"), strToLocalDateTime("05-04-2024 01:00:00"));
        Schedule schedule = ScheduleFactory.generate(datePickerDeparture.getValue().toString());
        System.out.println(schedule.toString());
        HBox hBoxSchedule = new HBox();
        ImageView imgViewAirline = new ImageView();

        return hBoxSchedule;
    }
}