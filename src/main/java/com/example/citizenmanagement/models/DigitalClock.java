package com.example.citizenmanagement.models;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DigitalClock {
    public static void TimeRunning(Label timeLabel, Label dateLabel) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH : mm");
            timeLabel.setText(currentTime.format(dtf1));

            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy");
            dateLabel.setText(currentTime.format(dtf2));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
