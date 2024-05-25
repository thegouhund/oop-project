package com.project.controller;

import com.project.App;
import com.project.controller.router.AppRouter;
import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {
    @FXML
    private MFXTextField fieldUsername;
    @FXML
    private MFXTextField fieldPassword;
    @FXML
    private MFXButton btnLogin;
    private AppRouter appRouter;

    @FXML
    private void initialize(){
        appRouter = AppRouter.getInstance();
    }

    @FXML
    private void onLogin(ActionEvent event) {
        UserDAO userDAO = new UserDAO(DatabaseUtils.getConnection());
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        User user = userDAO.login(username, password);

        if (user != null) {
            if(user.isAdmin()){
                // TODO switch to Beli Tiket Router Needed???
                appRouter.navigate(event, "dashboard/Dashboard.fxml");
            } else {
                appRouter.navigate(event, "FormScene.fxml");
            }
        } else {
                // TODO create dialog "Username atau Password Salah"
        }
    }
}
