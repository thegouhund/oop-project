package com.project.controller.dashboard.ticket;

import com.project.controller.Controller;
import com.project.model.Passenger;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.ArrayList;


public class TablePassengerController extends Controller {
    @FXML
    private MFXTableView<Passenger> tblPassenger;

    public void setSelectedPassenger(ArrayList<Passenger> passengers) {
        ObservableList<Passenger> observableListPassengers = FXCollections.observableArrayList(passengers);

        MFXTableColumn<Passenger> passengerNameColumn = new MFXTableColumn<>("Nama");
        MFXTableColumn<Passenger> passengerAgeColumn = new MFXTableColumn<>("Usia");

        passengerNameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Passenger::getName));
        passengerAgeColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Passenger::getAge));
        passengerNameColumn.setColumnResizable(true);

        tblPassenger.getTableColumns().add(passengerNameColumn);
        tblPassenger.getTableColumns().add(passengerAgeColumn);
        tblPassenger.setItems(observableListPassengers);
    }
}
