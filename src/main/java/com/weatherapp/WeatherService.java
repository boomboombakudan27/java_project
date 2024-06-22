package com.weatherapp;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "07b388433b824e59af671259242206"; // Replace with your API key
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=";

    public WeatherData fetchWeatherData(String location) {
        try {
            URL url = new URL(API_URL + location);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Reader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            WeatherData data = gson.fromJson(reader, WeatherData.class);

            reader.close();
            connection.disconnect();

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
