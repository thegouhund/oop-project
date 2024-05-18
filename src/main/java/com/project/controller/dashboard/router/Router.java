package com.project.controller.dashboard.router;

import com.project.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Router {
    private static Router instance;
    private final StackPane stackPaneMain;

    private Router(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }

    public static Router getInstance(StackPane stackPaneMain) {
        if (instance == null) {
            instance = new Router(stackPaneMain);
        }
        return instance;
    }

    public Controller navigate(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/views/" + fxmlPath));
            Parent root = loader.load();
            Object controller = loader.getController();
            stackPaneMain.getChildren().clear();
            stackPaneMain.getChildren().add(root);
            return (Controller) controller;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}