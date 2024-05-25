package com.project.controller.dashboard.airport;

import com.project.controller.Controller;
import com.project.controller.router.DashboardRouter;
import com.project.dao.AirlineDAO;
import com.project.dao.AirportDAO;
import com.project.model.Airline;
import com.project.model.Airport;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class DashboardAddAirportController extends Controller {
    @FXML
    private MFXTextField airportIataField;
    @FXML
    private MFXTextField airportCityField;
    @FXML
    private VBox vBoxMain;
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }

    @FXML
    private void initialize() {
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }

    @FXML
    private void onSubmit() {
        String airportIata = airportIataField.getText();
        String airportCity = airportCityField.getText();

        AirportDAO airportDAO = new AirportDAO(DatabaseUtils.getConnection());
        airportDAO.add(new Airport(airportIata, airportCity));

        goBack();
    }

    private void goBack() {
        DashboardAirportController controller = (DashboardAirportController) dashboardRouter.navigate("dashboard/airport/DashboardAirport.fxml");
        controller.setStackPane(stackPaneMain);
    }
}
