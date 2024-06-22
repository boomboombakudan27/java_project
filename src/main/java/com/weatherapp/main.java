package com.weatherapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        WeatherUI weatherUI = new WeatherUI();
        Scene scene = new Scene(weatherUI.getView(), 600, 400);

        primaryStage.setTitle("Weather Forecast Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
