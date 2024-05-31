package com.project.controller.dashboard.airport;

import com.project.controller.Controller;
import com.project.controller.dashboard.user.DashboardUserController;
import com.project.model.Airport;
import com.project.router.DashboardRouter;
import com.project.dao.AirportDAO;
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

public class DashboardAirportController extends Controller {
    @FXML
    private MFXTableView<Airport> tblAirport;
    @FXML
    private MFXButton btnAddAirport;
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;
    AirportDAO airportDAO = new AirportDAO(DatabaseUtils.getConnection());

    public void initialize() {

        MFXTableColumn<Airport> iataColumn = new MFXTableColumn<>("IATA", true);
        MFXTableColumn<Airport> cityColumn = new MFXTableColumn<>("Kota", true, Comparator.comparing(Airport::getCity));
        MFXTableColumn<Airport> nameColumn = new MFXTableColumn<>("Nama", true, Comparator.comparing(Airport::getName));
//        iataColumn.setPrefWidth(755);
        iataColumn.setRowCellFactory(airport -> new MFXTableRowCell<>(Airport::getIata));
        cityColumn.setRowCellFactory(airport -> new MFXTableRowCell<>(Airport::getCity));
        nameColumn.setRowCellFactory(airport -> new MFXTableRowCell<>(Airport::getName));

        iataColumn.setMinWidth(150);
        cityColumn.setMinWidth(250);

        tblAirport.getTableColumns().add(iataColumn);
        tblAirport.getTableColumns().add(cityColumn);
        tblAirport.getTableColumns().add(nameColumn);
        tblAirport.setItems(FXCollections.observableArrayList(airportDAO.getAll()));
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPaneMain = stackPane;
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }

    @FXML
    public void createAddAirportWindow() {
        DashboardAddAirportController controller = (DashboardAddAirportController) dashboardRouter.navigate("dashboard/airport/AddAirport.fxml");
        controller.setStackPane(stackPaneMain);
    }

    @FXML
    public void createEditAirportWindow() {
        Airport selectedAirport = tblAirport.getSelectionModel().getSelectedValue();
        System.out.println(selectedAirport);
        if (selectedAirport != null) {
            DashboardEditAirportController controller = (DashboardEditAirportController) dashboardRouter.navigate("dashboard/airport/EditAirport.fxml");
            controller.setStackPane(stackPaneMain);
            controller.setSelectedAirport(tblAirport.getSelectionModel().getSelectedValue());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pilih Bandara");
            alert.setContentText("Pilih bandara yang ingin diubah!");
            alert.showAndWait();
        }
    }

    @FXML
    public void deleteSelected() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Konfirmasi");
        alert.setContentText("Apakah anda ingin menghapus?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            airportDAO.delete(tblAirport.getSelectionModel().getSelectedValue().getId());
            DashboardAirportController controller = (DashboardAirportController) dashboardRouter.navigate("dashboard/airport/DashboardAirport.fxml");
            controller.setStackPane(stackPaneMain);
        }
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnAddAirport) {
            createAddAirportWindow();
        }
    }

}
