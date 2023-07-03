package at.tourplannerapp;

import at.tourplannerapp.service.weather.WeatherApiClient;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherApiClientTest {

    @Test
    void makeApiCallTest() {
        // Arrange
        WeatherApiClient client = new WeatherApiClient();

        // Act
        client.makeApiCall("Vienna");

        // Assert
        assertNotNull(client.getResponse());
    }

    @Test
    void getConditionOfDayTest() {
        // Arrange
        WeatherApiClient client = new WeatherApiClient();
        client.makeApiCall("Vienna");

        // Act
        String conditionOfDay = client.getConditionOfDay();

        // Assert
        assertNotNull(conditionOfDay);
    }

    @Test
    void getIconUrlTest() {
        // Arrange
        WeatherApiClient client = new WeatherApiClient();
        client.makeApiCall("Vienna");

        // Act
        String iconUrl = client.getIconUrl();

        // Assert
        assertNotNull(iconUrl);
    }

    @Test
    void downloadImageTest() {
        // Arrange
        WeatherApiClient client = new WeatherApiClient();
        client.makeApiCall("Vienna");

        // Act
        Image image = client.downloadImage();

        // Assert
        assertNotNull(image);
    }
}
