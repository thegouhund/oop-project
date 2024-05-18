package com.project.controller.dashboard.airline;

import com.project.controller.Controller;
import com.project.controller.router.DashboardRouter;
import com.project.dao.AirlineDAO;
import com.project.model.Airline;
import com.project.utils.DatabaseUtils;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.*;
import javafx.scene.layout.StackPane;


public class DashboardEditAirlineController extends Controller {
    private Airline selectedAirline;
    private StackPane stackPaneMain;
    @FXML
    private MFXTextField airlineNameField;
    @FXML
    private MFXTextField airlineCodeField;
    private DashboardRouter dashboardRouter;

    public DashboardEditAirlineController() {
    }

    public void setSelectedAirline(Airline selectedAirline) {
        System.out.println(selectedAirline);
        this.selectedAirline = selectedAirline;
        this.airlineNameField.setText(selectedAirline.getName());
        this.airlineCodeField.setText(selectedAirline.getAirlineCode());
    }

    @FXML
    private void initialize(){
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
        System.out.println(selectedAirline);
    }

    @FXML
    private void onSubmit() {
        String airlineName = airlineNameField.getText();
        String airlineCode = airlineCodeField.getText();

        AirlineDAO airlineDAO = new AirlineDAO(DatabaseUtils.getConnection());
        airlineDAO.update(new Airline(airlineName), selectedAirline.getId());

        goBack();
    }

    private void goBack() {
        DashboardAirlineController controller = (DashboardAirlineController) dashboardRouter.navigate("DashboardAirline.fxml");
        controller.setStackPane(stackPaneMain);
    }

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }
}
