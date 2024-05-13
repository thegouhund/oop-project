package com.project.controller.dashboard;

import com.project.dao.AirlineDAO;
import com.project.model.Airline;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.Comparator;

public class DashboardAirlineController {
    @FXML
    private MFXTableView<Airline> tblAirline;
    @FXML
    private MFXButton btnAddAirline;

    public void initialize() {
        AirlineDAO airlineDAO = new AirlineDAO(DatabaseUtils.getConnection());

        MFXTableColumn<Airline> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Airline::getName));
        nameColumn.setPrefWidth(755);
        nameColumn.setRowCellFactory(airline -> new MFXTableRowCell<>(Airline::getName));
        tblAirline.getTableColumns().add(nameColumn);
        tblAirline.setItems(FXCollections.observableArrayList(airlineDAO.getAll()));
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAddAirline) {
            System.out.println(tblAirline.getSelectionModel().getSelectedValue());
        }
    }
}
