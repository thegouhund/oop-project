package com.tubes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import java.io.File;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField fieldName;
    @FXML
    private SearchableComboBox<String> cbFrom;
    @FXML
    private SearchableComboBox<String> cbDestination;
    @FXML
    private VBox vboxMain;

    @FXML
    private void initialize() {
        cbFrom.setItems(getAirportsChoice());
        cbDestination.setItems(getAirportsChoice());

    }

    @FXML
    private void onSubmit() {
        System.out.println("button");
        System.out.println(fieldName.getText());
        System.out.println(cbFrom.getValue());
    }

    private ObservableList<String> getAirportsChoice() {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = objectMapper.readTree(new File("C:\\Users\\HP\\IdeaProjects\\tubes\\src\\main\\java\\com\\tubes\\airports.json"));
            ObservableList<String> options = FXCollections.observableArrayList();
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
        vboxMain.getChildren().add(passengerAmount + 2, new Label("Nama Penumpang " + passengerAmount));
        vboxMain.getChildren().add(passengerAmount + 3, new TextField());
    }

    private int getPassengerAmount() {
        int counter = 1;
        for (Object nodes : vboxMain.getChildren()) {
            if (nodes instanceof Label) {
                counter++;
            }
        }
        return counter;
    }
}