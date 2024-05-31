package com.project.components;

import com.project.model.Schedule;
import com.project.utils.TimeUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.text.DecimalFormat;

public class ScheduleUI extends HBox {
    MFXButton actionButton = new MFXButton();
    boolean showBuyButton;

    public ScheduleUI(Schedule schedule, boolean showBuyButton) {
        this.showBuyButton = showBuyButton;
        setupHBox();
        setupImageView(schedule);
        setupTimeInfo(schedule);
        setupIata(schedule);
        setupPrice(schedule);
        setupButton();

        this.setStyle("-fx-border-width: 1;-fx-border-color:grey;-fx-border-radius:6px");
    }


    private void setupHBox() {
        this.setPadding(new Insets(8, 8, 8, 8));
        this.setSpacing(16);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    private void setupImageView(Schedule schedule) {
        ImageView imgViewLogo = new ImageView();
        String file_dir = "src/main/resources/com/project/img/airlines/";
        String filename = schedule.getAirline().getName().toLowerCase().replace(" ", "").replace("\"", "") + ".png";
        File file = new File(file_dir + filename);

        Image imgLogo = new Image(file.toURI().toString());
        imgViewLogo.setImage(imgLogo);
        imgViewLogo.setFitHeight(35);
        imgViewLogo.setFitWidth(100);
        this.getChildren().add(imgViewLogo);
    }

    private void setupTimeInfo(Schedule schedule) {
        VBox vBoxTimeInfo = new VBox();
        Label dateLabel = new Label(TimeUtils.localDateGetDate(schedule.getArrivalTime()));
        Label timeLabel = new Label(TimeUtils.localDateGetTime(schedule.getDepartureTime()) + " - " + TimeUtils.localDateGetTime(schedule.getArrivalTime()));
        Label travelTime = new Label("Travel time: 3 hours"); // TODO

        dateLabel.setFont(new Font(14));
        timeLabel.setFont(new Font(14));

        vBoxTimeInfo.getChildren().addAll(dateLabel, timeLabel, travelTime);
        this.getChildren().add(vBoxTimeInfo);
    }

    private void setupIata(Schedule schedule) {
        Label iata = new Label(schedule.getAirportFrom().getIata() + " - " + schedule.getAirportDestination().getIata());
        iata.setFont(new Font(18));
        iata.setPrefWidth(100);
        this.getChildren().add(iata);
    }

    private void setupPrice(Schedule schedule) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String price = decimalFormat.format(schedule.getPrice());
        price = price.replace(",", ".");
        Label labelPrice = new Label("IDR " + price);

        labelPrice.setFont(new Font(18));
        labelPrice.setPrefWidth(200);
        labelPrice.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().add(labelPrice);
    }

    private void setupButton() {
        if (showBuyButton) {
            actionButton.setText("Beli");
            actionButton.setStyle("-fx-background-color: #5CDB5C;-fx-text-fill:white;");
            actionButton.setPrefWidth(80);
        } else {
            actionButton.setText("Cek Penumpang");
            actionButton.setStyle("-fx-background-color: #1B76FC;-fx-text-fill:white;");
            actionButton.setPrefWidth(160);
        }

        actionButton.setPrefHeight(40);
        actionButton.setRippleColor(Paint.valueOf("#85e485"));

        this.getChildren().add(actionButton);
    }

    public void setButtonAction(EventHandler<ActionEvent> e) {
        actionButton.setOnAction(e);
    }

}