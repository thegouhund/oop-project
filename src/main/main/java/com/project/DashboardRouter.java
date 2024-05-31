package com.project.router;

import com.project.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DashboardRouter {
    private static DashboardRouter instance;
    private final StackPane stackPaneMain;

    private DashboardRouter(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }

    public static DashboardRouter getInstance(StackPane stackPaneMain) {
        if (instance == null) {
            instance = new DashboardRouter(stackPaneMain);
        }
        return instance;
    }

    public Controller navigate(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + fxmlPath));
            Parent root = loader.load();
            Controller controller = loader.getController();
            stackPaneMain.getChildren().clear();
            stackPaneMain.getChildren().add(root);
            return controller;
        } catch (IOException e) {
            System.out.println("Error loading FXML file: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

}