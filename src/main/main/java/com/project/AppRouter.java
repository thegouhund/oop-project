package com.project.router;

import com.project.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;

public class AppRouter {
    private static AppRouter instance;

    public static AppRouter getInstance() {
        if (instance == null) {
            instance = new AppRouter();
        }
        return instance;
    }

    public Controller navigate(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + fxmlPath));
            Parent root = loader.load();
            Controller controller = loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return controller;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}