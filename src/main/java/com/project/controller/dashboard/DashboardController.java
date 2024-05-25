package com.project.controller.dashboard;

import com.project.controller.Controller;
import com.project.controller.dashboard.airline.DashboardAirlineController;
import com.project.controller.dashboard.airport.DashboardAirportController;
import com.project.controller.dashboard.ticket.DashboardTicketController;
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
    private Button btnAirport;
    @FXML
    private Button btnTicket;
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


    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnOverview) {
            dashboardRouter.navigate("dashboard/DashboardOverview.fxml");
        }
        if (actionEvent.getSource() == btnAirlines) {
            DashboardAirlineController controller = (DashboardAirlineController) dashboardRouter.navigate("dashboard/airline/DashboardAirline.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (actionEvent.getSource() == btnAirport) {
            DashboardAirportController controller = (DashboardAirportController) dashboardRouter.navigate("dashboard/airport/DashboardAirport.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (actionEvent.getSource() == btnTicket) {
            DashboardTicketController controller = (DashboardTicketController) dashboardRouter.navigate("dashboard/ticket/DashboardTicket.fxml");
            controller.setStackPane(stackPaneMain);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/dashboard/ticket/DashboardTicket.fxml"));
//            Parent root = loader.load();
//            Object controller = loader.getController();
//            stackPaneMain.getChildren().clear();
//            stackPaneMain.getChildren().add(root);
        }
    }

//    private void openAirlinePane() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/DashboardAirport.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/dashboard/DashboardOverview.fxml"));
            Parent root = loader.load();

            stackPaneMain.getChildren().clear();
            stackPaneMain.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
