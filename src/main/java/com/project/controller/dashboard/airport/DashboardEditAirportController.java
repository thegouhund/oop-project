package com.project.controller.dashboard.airport;

import com.project.controller.Controller;
import com.project.router.DashboardRouter;
import com.project.dao.AirportDAO;
import com.project.model.Airport;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;


public class DashboardEditAirportController extends Controller {
    private Airport selectedAirport;
    private StackPane stackPaneMain;
    @FXML
    private MFXTextField airportIataField, airportCityField, airportNameField;
    private DashboardRouter dashboardRouter;

    public void setSelectedAirport(Airport selectedAirport) {
        System.out.println(selectedAirport);
        this.selectedAirport = selectedAirport;
        this.airportIataField.setText(selectedAirport.getIata());
        this.airportCityField.setText(selectedAirport.getCity());
        this.airportNameField.setText(selectedAirport.getName());
    }

    @FXML
    private void initialize() {
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
        System.out.println(selectedAirport);
    }

    @FXML
    private void onSubmit() {
        String airportIata = airportIataField.getText();
        String airportCity = airportCityField.getText();
        String airportName = airportNameField.getText();

        AirportDAO airportDao = new AirportDAO(DatabaseUtils.getConnection());
        airportDao.update(new Airport(airportIata, airportCity, airportName), selectedAirport.getId());

        goBack();
    }

    private void goBack() {
        DashboardAirportController controller = (DashboardAirportController) dashboardRouter.navigate("dashboard/airport/DashboardAirport.fxml");
        controller.setStackPane(stackPaneMain);
    }

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }
}
