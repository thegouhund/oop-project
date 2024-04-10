module com.tubes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires MaterialFX;
    requires java.sql;
//    requires io.github.palexdev.materialfx;

    opens com.project to javafx.fxml;
    exports com.project;
    exports com.project.controller;
    opens com.project.controller to javafx.fxml;
    exports com.project.utils;
    opens com.project.utils to javafx.fxml;
}