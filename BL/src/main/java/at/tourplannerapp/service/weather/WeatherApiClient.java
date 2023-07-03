package at.tourplannerapp.service.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    private static final String API_KEY = "7efe14a3d42040719b8112117230307";

    private String response;

    public static void main(String[] args) {
        String location = "Wiener Neustadt".replace(" ", "%20");
        WeatherApiClient client = new WeatherApiClient();
        client.makeApiCall(location);
        System.out.println(client.getConditionOfDay());
    }

    public void makeApiCall(String location) {

        String apiUrl = "http://api.weatherapi.com/v1/forecast.json?key="+API_KEY+"&q="+location+"&days=1&aqi=no&alerts=no";

        try {
            // Send API request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read API response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();

            response = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getConditionOfDay() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            JsonNode forecastNode = rootNode.path("forecast").path("forecastday");
            if (forecastNode.isArray() && forecastNode.size() > 0) {
                JsonNode dayNode = forecastNode.get(0).path("day");
                if (dayNode.isObject()) {
                    JsonNode conditionNode = dayNode.path("condition");
                    if (conditionNode.isObject()) {
                        return conditionNode.path("text").asText();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIconUrl() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            JsonNode conditionNode = rootNode.path("current").path("condition");
            if (conditionNode.isObject()) {
                return conditionNode.path("icon").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage downloadImage() {
        try {
            URL url = new URL(getIconUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
