package com.project.controller;

import javafx.scene.layout.StackPane;

public abstract class Controller {
    protected StackPane stackPaneMain;

    public void setStackPane(StackPane stackPaneMain) {
        System.out.println(stackPaneMain);
        this.stackPaneMain = stackPaneMain;
    }
}
