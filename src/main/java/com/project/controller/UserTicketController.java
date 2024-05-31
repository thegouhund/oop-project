package com.project.controller;

import com.project.components.ScheduleUI;
import com.project.dao.TicketDAO;
import com.project.model.Passenger;
import com.project.model.Schedule;
import com.project.model.Ticket;
import com.project.model.User;
import com.project.router.AppRouter;
import com.project.utils.DatabaseUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UserTicketController extends Controller {
    @FXML
    private VBox vBoxTicket;

    TicketDAO ticketDAO = new TicketDAO(DatabaseUtils.getConnection());
    private final AppRouter appRouter = AppRouter.getInstance();
    private final User user;

    public UserTicketController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        ArrayList<Ticket> tickets = ticketDAO.getByUser(user);
        for (Ticket ticket : tickets) {
            Schedule schedule = ticket.getSchedule();
            ScheduleUI scheduleUI = new ScheduleUI(schedule, false);
            scheduleUI.setButtonAction(e -> onPassengerCheck(ticket));

            vBoxTicket.getChildren().add(scheduleUI);
        }
    }

    public void onPassengerCheck(Ticket ticket) {
        StringBuilder content = new StringBuilder("List Penumpang: \n" );
        ArrayList<Passenger> passengers = ticket.getPassengers();
        for (Passenger passenger : passengers) {
            content.append(passenger.getName()).append("\n" );
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pesan" );
        alert.setHeaderText("Informasi Penumpang" );
        alert.setContentText(content.toString().toString());
        alert.showAndWait();
    }

    @FXML
    private void switchToLogin(ActionEvent event){
        appRouter.navigate(event, "MasukAkun.fxml");
    }

    @FXML
    public void switchToForm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + "FormScene.fxml" ));
        loader.setControllerFactory(c -> new FormController(user));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
