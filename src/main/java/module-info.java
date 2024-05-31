module com.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires MaterialFX;
    requires java.sql;
    requires java.xml.crypto;
//    requires io.github.palexdev.materialfx;

    opens com.project to javafx.fxml;
    exports com.project;
    exports com.project.controller;
    opens com.project.model to javafx.fxml;
    exports com.project.model;
    opens com.project.controller to javafx.fxml;
    exports com.project.utils;
    opens com.project.utils to javafx.fxml;
    exports com.project.controller.dashboard;
    opens com.project.controller.dashboard to javafx.fxml;
    opens com.project.router to javafx.fxml;
    exports com.project.router;
    opens com.project.controller.dashboard.airline to javafx.fxml;
    exports com.project.controller.dashboard.airline;
    opens com.project.controller.dashboard.airport to javafx.fxml;
    exports com.project.controller.dashboard.airport;
    opens com.project.controller.dashboard.ticket to javafx.fxml;
    exports com.project.controller.dashboard.ticket;
    opens com.project.controller.dashboard.user to javafx.fxml;
    exports com.project.controller.dashboard.user;

}