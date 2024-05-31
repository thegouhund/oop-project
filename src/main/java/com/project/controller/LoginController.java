package com.project.controller;

import com.project.controller.dashboard.DashboardController;
import com.project.router.AppRouter;
import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller {
    @FXML
    private MFXTextField fieldUsername;
    @FXML
    private MFXTextField fieldPassword;
    @FXML
    private AppRouter appRouter;

    @FXML
    private void initialize() {
        appRouter = AppRouter.getInstance();
    }

    @FXML
    private void onLogin(ActionEvent event) throws IOException {
        UserDAO userDAO = new UserDAO(DatabaseUtils.getConnection());
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        User user = userDAO.login(username, password);

        if (user != null) {
            if (user.isAdmin()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + "dashboard/Dashboard.fxml" ));
                loader.setControllerFactory(c -> new DashboardController(user));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + "UserTicket.fxml" ));
                loader.setControllerFactory(c -> new UserTicketController(user));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error" );
            alert.setHeaderText("Username/Password Error" );
            alert.setContentText("Username/Password yang anda masukkan salah!" );
            alert.showAndWait();
        }
    }

    @FXML
    private void switchToRegister(ActionEvent event) {
        appRouter.navigate(event, "RegistrasiAkun.fxml" );
    }
}
