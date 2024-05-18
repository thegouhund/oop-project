package com.project.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import io.github.palexdev.mfxresources.fonts.MFXIconWrapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FirstSceneController extends Controller {
    @FXML
    private MFXButton btnSwitchToForm;
    @FXML
    private MFXButton btnSwitchToDashboard;
    @FXML
    private MFXIconWrapper iconSwitchToForm;
    @FXML
    private MFXIconWrapper iconSwitchToDashboard;

    @FXML
    private void initialize() {
        iconSwitchToForm.setIcon(new MFXFontIcon("fas-ticket"));
        iconSwitchToDashboard.setIcon(new MFXFontIcon("fas-key"));
    }

    @FXML
    public void switchToFormScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/views/FormScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/views/Dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
