package com.project.controller.dashboard;

import com.project.controller.Controller;
import com.project.dao.AirlineDAO;
import com.project.dao.AirportDAO;
import com.project.dao.TicketDAO;
import com.project.dao.UserDAO;
import com.project.model.Ticket;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;

public class DashboardOverviewController extends Controller {
    @FXML
    private Label labelTicketTotal;
    @FXML
    private Label labelAirlineTotal;
    @FXML
    private Label labelAirportTotal;
    @FXML
    private Label labelUserTotal;


    @FXML
    public void initialize() {
        Connection conn = DatabaseUtils.getConnection();

        TicketDAO ticketDAO = new TicketDAO(conn);
        AirlineDAO airlineDAO = new AirlineDAO(conn);
        AirportDAO airportDAO = new AirportDAO(conn);
        UserDAO userDAO = new UserDAO(conn);

        labelTicketTotal.setText(String.valueOf(ticketDAO.size()));
        labelAirlineTotal.setText(String.valueOf(airlineDAO.size()));
        labelAirportTotal.setText(String.valueOf(airportDAO.size()));
        labelUserTotal.setText(String.valueOf(userDAO.size()));
    }
}
