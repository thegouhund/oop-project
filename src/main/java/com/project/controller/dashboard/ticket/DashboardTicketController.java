package com.project.controller.dashboard.ticket;

import com.project.controller.Controller;
import com.project.controller.dashboard.airport.DashboardEditAirportController;
import com.project.controller.router.DashboardRouter;
import com.project.dao.TicketDAO;
import com.project.model.Ticket;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Connection;


public class DashboardTicketController extends Controller {
    @FXML
    private MFXTableView<Ticket> tblTicket;
    @FXML
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;

    public void initialize() {
        Connection conn = DatabaseUtils.getConnection();
        TicketDAO ticketDAO = new TicketDAO(conn);
        ObservableList<Ticket> tickets = FXCollections.observableArrayList(ticketDAO.getBySchedule());

        MFXTableColumn<Ticket> airlineNameColumn = new MFXTableColumn<>("Maskapai");
        MFXTableColumn<Ticket> airportFromColumn = new MFXTableColumn<>("Bandara Asal", false);
        MFXTableColumn<Ticket> airportDestinationColumn = new MFXTableColumn<>("Bandara Tujuan", false);
        MFXTableColumn<Ticket> scheduleDepartureTime = new MFXTableColumn<>("Waktu Berangkat", false);
        MFXTableColumn<Ticket> schedulePrice = new MFXTableColumn<>("Harga", false);

        airlineNameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(ticket -> ticket.getSchedule().getAirline().getName()));
        airportFromColumn.setRowCellFactory(device -> new MFXTableRowCell<>(ticket -> ticket.getSchedule().getAirportFrom().getIata()));
        airportDestinationColumn.setRowCellFactory(device -> new MFXTableRowCell<>(ticket -> ticket.getSchedule().getAirportDestination().getIata()));
        scheduleDepartureTime.setRowCellFactory(device -> new MFXTableRowCell<>(ticket -> ticket.getSchedule().getDepartureTime()));
        schedulePrice.setRowCellFactory(device -> new MFXTableRowCell<>(ticket -> ticket.getSchedule().getPrice()));

        scheduleDepartureTime.setPrefWidth(200);

        tblTicket.getTableColumns().add(airlineNameColumn);
        tblTicket.getTableColumns().add(airportFromColumn);
        tblTicket.getTableColumns().add(airportDestinationColumn);
        tblTicket.getTableColumns().add(scheduleDepartureTime);
        tblTicket.getTableColumns().add(schedulePrice);
        tblTicket.setItems(tickets);
    }

    @FXML
    public void createTablePassengerForSelectedTicket() throws IOException {
        System.out.println("PASSENGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER" + tblTicket.getSelectionModel().getSelectedValue().getPassengers());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + "dashboard/ticket/TablePassenger.fxml"));
        Parent root = loader.load();
        TablePassengerController controller = loader.getController();
        stackPaneMain.getChildren().clear();
        stackPaneMain.getChildren().add(root);
        controller.setSelectedPassenger(tblTicket.getSelectionModel().getSelectedValue().getPassengers());
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPaneMain = stackPane;
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }

//    public void createAddTicketWindow() {
//        stackPaneMain.setStyle("-fx-background-color: white");
//        DashboardAddTicketController controller = (DashboardAddTicketController) dashboardRouter.navigate("dashboard/ticket/AddTicket.fxml");
//        controller.setStackPane(stackPaneMain);
//    }
}
