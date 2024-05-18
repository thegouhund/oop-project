package com.project.controller.dashboard;

import com.project.controller.Controller;
import com.project.controller.dashboard.airline.DashboardAirlineController;
import com.project.controller.router.DashboardRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends Controller implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private StackPane stackPaneMain;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnAirlines;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Pane pnlMenus;
    private DashboardRouter dashboardRouter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openOverviewPane();
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : white");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : white");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            dashboardRouter.navigate("DashboardOverview.fxml");
        }
        if (actionEvent.getSource() == btnAirlines) {
            DashboardAirlineController controller = (DashboardAirlineController) dashboardRouter.navigate("DashboardAirline.fxml");
            controller.setStackPane(stackPaneMain);
        }
    }

//    private void openAirlinePane() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/DashboardAirline.fxml"));
//            Parent root = loader.load();
//            DashboardAirlineController controller = loader.getController();
//            controller.initData(stackPaneMain);
//            stackPaneMain.getChildren().clear();
//            stackPaneMain.getChildren().add(root);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void openOverviewPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/DashboardOverview.fxml"));
            Parent root = loader.load();

            stackPaneMain.getChildren().clear();
            stackPaneMain.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
