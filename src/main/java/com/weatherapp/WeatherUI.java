package com.weatherapp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeatherUI {
    private VBox root;
    private TextField cityInput;
    private Button fetchButton;
    private VBox weatherDisplay;

    public WeatherUI() {
        root = new VBox(10);
        root.setPadding(new Insets(15, 15, 15, 15));

        HBox inputBox = new HBox(10);
        cityInput = new TextField();
        cityInput.setPromptText("Enter city name");
        fetchButton = new Button("Fetch Weather");

        inputBox.getChildren().addAll(cityInput, fetchButton);

        weatherDisplay = new VBox(10);
        weatherDisplay.setPadding(new Insets(15, 0, 0, 0));

        root.getChildren().addAll(inputBox, weatherDisplay);

        fetchButton.setOnAction(e -> fetchAndDisplayWeather());
    }

    public VBox getView() {
        return root;
    }

    private void fetchAndDisplayWeather() {
        String city = cityInput.getText().trim();
        if (!city.isEmpty()) {
            WeatherService weatherService = new WeatherService();
            WeatherData weatherData = weatherService.fetchWeatherData(city);
            displayWeather(weatherData);
        }
    }

    private void displayWeather(WeatherData weatherData) {
        weatherDisplay.getChildren().clear();
        if (weatherData != null) {
            Label cityLabel = new Label("Weather in " + weatherData.getLocation().getName() + ", " + weatherData.getLocation().getCountry());
            Label tempLabel = new Label("Temperature: " + weatherData.getCurrent().getTemp_c() + "°C");
            Label conditionLabel = new Label("Condition: " + weatherData.getCurrent().getCondition().getText());

            weatherDisplay.getChildren().addAll(cityLabel, tempLabel, conditionLabel);
        } else {
            weatherDisplay.getChildren().add(new Label("Failed to fetch weather data."));
        }
    }
}
