package com.project.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class LoginController {
    @FXML
    private MFXTextField fieldUsername;
    @FXML
    private MFXTextField fieldPassword;
    @FXML
    private MFXButton btnLogin;

    @FXML
    private void onLogin(){
        System.out.println("Login diklik!");
    }
}
