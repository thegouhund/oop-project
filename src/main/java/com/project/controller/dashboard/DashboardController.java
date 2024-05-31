package com.project.controller.dashboard;

import com.project.controller.Controller;
import com.project.controller.dashboard.airline.DashboardAirlineController;
import com.project.controller.dashboard.airport.DashboardAirportController;
import com.project.controller.dashboard.ticket.DashboardTicketController;
import com.project.controller.dashboard.user.DashboardUserController;
import com.project.model.User;
import com.project.router.AppRouter;
import com.project.router.DashboardRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DashboardController extends Controller {
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
    private Button btnSignout;
    @FXML
    private Button btnUser;
    @FXML
    private Label labelAdminUsername;
    @FXML
    private User user;

    private DashboardRouter dashboardRouter;
    private AppRouter appRouter;

    public DashboardController(User user) {
        this.user = user;
    }

    public void initialize() {
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
        appRouter = AppRouter.getInstance();
        labelAdminUsername.setText(user.getUsername());
        openOverviewPane();
    }

    public void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnOverview) {
            DashboardOverviewController controller = (DashboardOverviewController) dashboardRouter.navigate("dashboard/DashboardOverview.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (event.getSource() == btnAirlines) {
            DashboardAirlineController controller = (DashboardAirlineController) dashboardRouter.navigate("dashboard/airline/DashboardAirline.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (event.getSource() == btnAirport) {
            DashboardAirportController controller = (DashboardAirportController) dashboardRouter.navigate("dashboard/airport/DashboardAirport.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (event.getSource() == btnTicket) {
            DashboardTicketController controller = (DashboardTicketController) dashboardRouter.navigate("dashboard/ticket/DashboardTicket.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (event.getSource() == btnUser) {
            DashboardUserController controller = (DashboardUserController) dashboardRouter.navigate("dashboard/user/DashboardUser.fxml");
            controller.setStackPane(stackPaneMain);
        }
        if (event.getSource() == btnSignout) {
            System.out.println("Signing out");
            appRouter.navigate(event, "MasukAkun.fxml");
        }
    }

    private void openOverviewPane() {
        DashboardOverviewController controller = (DashboardOverviewController) dashboardRouter.navigate("dashboard/DashboardOverview.fxml");
        controller.setStackPane(stackPaneMain);
    }

    @FXML
    public void signout(ActionEvent event) { // TODO (blank stackpane after re login)
        appRouter.navigate(event, "MasukAkun.fxml");
    }
}
