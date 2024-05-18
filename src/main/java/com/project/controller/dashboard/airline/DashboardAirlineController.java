package com.project.controller.dashboard.airline;

import com.project.controller.Controller;
import com.project.controller.dashboard.router.Router;
import com.project.dao.AirlineDAO;
import com.project.model.Airline;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

public class DashboardAirlineController extends Controller {
    @FXML
    private MFXTableView<Airline> tblAirline;
    @FXML
    private MFXButton btnAddAirline;
    private Airline selectedAirline;
    private StackPane stackPaneMain;
    private Router router;

    public void initialize() {
        AirlineDAO airlineDAO = new AirlineDAO(DatabaseUtils.getConnection());

        MFXTableColumn<Airline> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Airline::getName));
        nameColumn.setPrefWidth(755);
        nameColumn.setRowCellFactory(airline -> new MFXTableRowCell<>(Airline::getName));
        tblAirline.getTableColumns().add(nameColumn);
        tblAirline.setItems(FXCollections.observableArrayList(airlineDAO.getAll()));
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPaneMain = stackPane;
        router = Router.getInstance(stackPaneMain);
    }

    public void createAddAirlineWindow() throws IOException {
        stackPaneMain.setStyle("-fx-background-color: white");
        DashboardAddAirlineController controller = (DashboardAddAirlineController) router.navigate("AddAirport.fxml");
        controller.setStackPane(stackPaneMain);
    }

    public void createEditAirlineWindow() throws IOException {
        stackPaneMain.setStyle("-fx-background-color: white");
        DashboardEditAirlineController controller = (DashboardEditAirlineController) router.navigate("EditAirport.fxml");
        controller.setStackPane(stackPaneMain);
        controller.setSelectedAirline(tblAirline.getSelectionModel().getSelectedValue());
    }

    public void createDeleteAirlineDialog() {
//        MFXGenericDialog dialog = new MFXGenericDialog();
//        dialog.setContentText("dsadsa");

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnAddAirline) {
            createAddAirlineWindow();
        }
    }
}