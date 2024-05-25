package com.project.controller;

import com.project.controller.router.AppRouter;
import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.*;

public class RegisterController {
    @FXML
    private MFXTextField fieldUsername;
    @FXML
    private MFXTextField fieldEmail;
    @FXML
    private MFXPasswordField fieldPassword;
    @FXML
    private MFXButton btnRegister;
    @FXML
    private MFXButton MasukAkun;
    private AppRouter appRouter;

    @FXML
    private void initialize() {
        appRouter = AppRouter.getInstance();
    }

    @FXML
    private void onRegister(ActionEvent event) {
        UserDAO userDAO = new UserDAO(DatabaseUtils.getConnection());
        String email = fieldEmail.getText();
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        User user = new User(email, username, password, false);
        userDAO.addAndGetGeneratedId(user);
    }

    @FXML
    private void switchToLogin(ActionEvent event){
        appRouter.navigate(event, "MasukAkun.fxml");
    }
}
