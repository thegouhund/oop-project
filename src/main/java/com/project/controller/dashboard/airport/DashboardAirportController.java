package com.project.controller.dashboard.airport;

import com.project.controller.Controller;
import com.project.controller.router.DashboardRouter;
import com.project.dao.AirportDAO;
import com.project.model.Airport;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Comparator;

public class DashboardAirportController extends Controller {
    public Pane pnlAirline;
    @FXML
    private MFXTableView<Airport> tblAirport;
    @FXML
    private MFXButton btnAddAirport;
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;

    public void initialize() {
        AirportDAO airportDAO = new AirportDAO(DatabaseUtils.getConnection());

        MFXTableColumn<Airport> iataColumn = new MFXTableColumn<>("IATA", false);
        MFXTableColumn<Airport> cityColumn = new MFXTableColumn<>("City", false, Comparator.comparing(Airport::getCity));
//        iataColumn.setPrefWidth(755);
        iataColumn.setRowCellFactory(airport -> new MFXTableRowCell<>(Airport::getIata));
        cityColumn.setRowCellFactory(airport -> new MFXTableRowCell<>(Airport::getCity));

        iataColumn.setPrefWidth(150);
        cityColumn.setPrefWidth(550);

        tblAirport.getTableColumns().add(iataColumn);
        tblAirport.getTableColumns().add(cityColumn);
        tblAirport.setItems(FXCollections.observableArrayList(airportDAO.getAll()));
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPaneMain = stackPane;
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }

    public void createAddAirportWindow() {
        stackPaneMain.setStyle("-fx-background-color: white");
        DashboardAddAirportController controller = (DashboardAddAirportController) dashboardRouter.navigate("dashboard/airport/AddAirport.fxml");
        controller.setStackPane(stackPaneMain);
    }

    public void createEditAirportWindow() {
        stackPaneMain.setStyle("-fx-background-color: white");
        DashboardEditAirportController controller = (DashboardEditAirportController) dashboardRouter.navigate("dashboard/airport/EditAirport.fxml");
        controller.setStackPane(stackPaneMain);
        controller.setSelectedAirport(tblAirport.getSelectionModel().getSelectedValue());
    }

    public void createDeleteAirportDialog() {
//        MFXGenericDialog dialog = new MFXGenericDialog();
//        dialog.setContentText("dsadsa");

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnAddAirport) {
            createAddAirportWindow();
        }
    }

    public void createEditAirlineWindow(ActionEvent actionEvent) {
    }
}
