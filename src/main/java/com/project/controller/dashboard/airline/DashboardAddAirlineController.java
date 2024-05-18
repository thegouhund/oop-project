package com.project.controller.dashboard.airline;

import com.project.controller.Controller;
import com.project.controller.dashboard.DashboardOverviewController;
import com.project.controller.dashboard.router.Router;
import com.project.dao.AirlineDAO;
import com.project.model.Airline;
import com.project.utils.DatabaseUtils;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class DashboardAddAirlineController extends Controller {
    @FXML
    private MFXTextField airlineNameField;
    @FXML
    private MFXTextField airlineCodeField;
    @FXML
    private VBox vBoxMain;
    private StackPane stackPaneMain;
    private Router router;

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }

    @FXML
    private void initialize() {
        router = Router.getInstance(stackPaneMain);
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
        DashboardAirlineController controller = (DashboardAirlineController) router.navigate("DashboardAirline.fxml");
        controller.setStackPane(stackPaneMain);
    }
}
