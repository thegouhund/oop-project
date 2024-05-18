package com.project.controller.dashboard;

import com.project.controller.Controller;
import com.project.controller.dashboard.airline.DashboardAirlineController;
import com.project.controller.dashboard.router.Router;
import com.project.model.Airline;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
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
import java.util.Comparator;
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
    private Router router;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openOverviewPane();
        router = Router.getInstance(stackPaneMain);
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
            router.navigate("DashboardOverview.fxml");
        }
        if (actionEvent.getSource() == btnAirlines) {
            DashboardAirlineController controller = (DashboardAirlineController) router.navigate("DashboardAirline.fxml");
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
