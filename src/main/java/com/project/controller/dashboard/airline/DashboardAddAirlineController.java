package com.project.controller.dashboard.airline;

import com.project.controller.Controller;
import com.project.controller.router.DashboardRouter;
import com.project.dao.AirlineDAO;
import com.project.model.Airline;
import com.project.utils.DatabaseUtils;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class DashboardAddAirlineController extends Controller {
    @FXML
    private MFXTextField airlineNameField;
    @FXML
    private MFXTextField airlineCodeField;
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
        String airlineName = airlineNameField.getText();
        String airlineCode = airlineCodeField.getText();

        AirlineDAO airlineDAO = new AirlineDAO(DatabaseUtils.getConnection());
        airlineDAO.add(new Airline(airlineName));

        goBack();
    }

    private void goBack() {
        DashboardAirlineController controller = (DashboardAirlineController) dashboardRouter.navigate("DashboardAirline.fxml");
        controller.setStackPane(stackPaneMain);
    }
}
