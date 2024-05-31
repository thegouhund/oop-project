package com.project.controller;

import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.router.AppRouter;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RegisterController extends Controller {
    @FXML
    private MFXTextField fieldEmail;
    @FXML
    private MFXTextField fieldUsername;
    @FXML
    private MFXPasswordField fieldPassword;

    private final AppRouter appRouter = AppRouter.getInstance();
    UserDAO userDAO = new UserDAO(DatabaseUtils.getConnection());

    @FXML
    public void onRegister(ActionEvent event) {
        boolean isValid = isValidInput();

        if (isValid) {
            String email = fieldEmail.getText();
            String username = fieldUsername.getText();
            String password = fieldPassword.getText();

            User user = new User(email, username, password, false);
            userDAO.add(user);
            sendAlert(true);
            appRouter.navigate(event, "MasukAkun.fxml" );
        } else {
            sendAlert(false);
        }
    }

    private boolean isValidInput() {
        boolean isValid = true;

        if (fieldEmail.getText().isEmpty()) {
            fieldEmail.setStyle("-fx-border-color: red;" );
            isValid = false;
        } else {
            fieldEmail.setStyle(null);
        }

        if (fieldUsername.getText().isEmpty()) {
            fieldUsername.setStyle("-fx-border-color: red;" );
            isValid = false;
        } else {
            fieldUsername.setStyle(null);
        }

        if (fieldPassword.getText().isEmpty()) {
            fieldPassword.setStyle("-fx-border-color: red;" );
            isValid = false;
        } else {
            fieldPassword.setStyle(null);
        }
        return isValid;
    }

    private void sendAlert(boolean isValid) {
        Alert alert;
        if (isValid) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registrasi Berhasil" );
            alert.setHeaderText("Registrasi Berhasil" );
            alert.setContentText("Silahkan login dengan username dan pasword!" );
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registrasi Error" );
            alert.setHeaderText("Text Field Kosong" );
            alert.setContentText("Isi email, username dan password anda!" );
            alert.showAndWait();
        }
    }

    @FXML
    private void switchToLogin(ActionEvent event) {
        appRouter.navigate(event, "MasukAkun.fxml" );
    }

}
