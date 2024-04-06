package com.project.views.components;

import com.project.model.Airline;
import com.project.model.Schedule;
import com.project.utils.TimeUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class ScheduleUI extends HBox {
    private Label price;

    public ScheduleUI(Schedule schedule) {
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
        String filename = "src/main/resources/com/project/img/airlines/" + schedule.getAirline().getName().toLowerCase().replace(" ", "").replace("\"", "") + ".png";
        File file = new File(filename);
        System.out.println(filename);

        Image imgLogo = new Image(file.toURI().toString());
        imgViewLogo.setImage(imgLogo);
        imgViewLogo.setFitHeight(50);
        imgViewLogo.setFitWidth(50);
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
        iata.setPrefWidth(147);
        this.getChildren().add(iata);
    }

    private void setupPrice(Schedule schedule) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String price = decimalFormat.format(schedule.getPrice());
        price = price.replace(",", ".");
        Label labelPrice = new Label("IDR " + price);
        labelPrice.setFont(new Font(18));
        labelPrice.setAlignment(Pos.CENTER);
        this.getChildren().add(labelPrice);
    }

    private void setupButton() {
        Button button = new Button("Buy");
        this.getChildren().add(button);
    }
}