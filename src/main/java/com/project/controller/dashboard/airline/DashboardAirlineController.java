package com.project.controller.dashboard.airline;

import com.project.controller.Controller;
import com.project.controller.dashboard.airline.DashboardAirlineController;
import com.project.controller.dashboard.airport.DashboardAirportController;
import com.project.router.DashboardRouter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

public class DashboardAirlineController extends Controller {
    @FXML
    private MFXTableView<Airline> tblAirline;
    @FXML
    private MFXButton btnAddAirline;
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;
    AirlineDAO airlineDAO = new AirlineDAO(DatabaseUtils.getConnection());

    public void initialize() {
        MFXTableColumn<Airline> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Airline::getName));
        MFXTableColumn<Airline> airlineCodeColumn = new MFXTableColumn<>("Airline Code", true, Comparator.comparing(Airline::getAirlineCode));
        nameColumn.setPrefWidth(200);
        nameColumn.setRowCellFactory(airline -> new MFXTableRowCell<>(Airline::getName));
        airlineCodeColumn.setRowCellFactory(airline -> new MFXTableRowCell<>(Airline::getAirlineCode));
        tblAirline.getTableColumns().add(nameColumn);
        tblAirline.getTableColumns().add(airlineCodeColumn);
        tblAirline.setItems(FXCollections.observableArrayList(airlineDAO.getAll()));
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPaneMain = stackPane;
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }

    public void createAddAirlineWindow() {
        DashboardAddAirlineController controller = (DashboardAddAirlineController) dashboardRouter.navigate("dashboard/airline/AddAirline.fxml" );
        controller.setStackPane(stackPaneMain);
    }

    public void createEditAirlineWindow() {
        Airline selectedAirline = tblAirline.getSelectionModel().getSelectedValue();

        if (selectedAirline != null) {
            DashboardEditAirlineController controller = (DashboardEditAirlineController) dashboardRouter.navigate("dashboard/airline/EditAirline.fxml" );
            controller.setSelectedAirline(selectedAirline);
            controller.setStackPane(stackPaneMain);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error" );
            alert.setHeaderText("Pilih Maskapai" );
            alert.setContentText("Pilih maskapai yang ingin diubah!" );
            alert.showAndWait();
        }
    }

    @FXML
    public void deleteSelected() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi" );
        alert.setHeaderText("Konfirmasi" );
        alert.setContentText("Apakah anda ingin menghapus?" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            airlineDAO.delete(tblAirline.getSelectionModel().getSelectedValue().getId());
            DashboardAirlineController controller = (DashboardAirlineController) dashboardRouter.navigate("dashboard/airline/DashboardAirline.fxml" );
            controller.setStackPane(stackPaneMain);
        }

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnAddAirline) {
            createAddAirlineWindow();
        }
    }
}
