package at.tourplannerapp;

import at.tourplannerapp.config.ApplicationConfigProperties;
import at.tourplannerapp.model.WeatherResponseModel;
import at.tourplannerapp.service.weather.WeatherService;
import at.tourplannerapp.service.weather.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherServiceImplTest {

    @Autowired
    ApplicationConfigProperties applicationConfigProperties;

    @Test
    void makeApiCallTest() {
        // Arrange
        WeatherService weatherService = new WeatherServiceImpl(applicationConfigProperties);

        // Act
        WeatherResponseModel weatherResponseModel = weatherService.getCurrentWeatherForecast("Vienna");

        // Assert
        assertNotNull(weatherResponseModel.getCurrentWeatherText());
        assertNotNull(weatherResponseModel.getIcon());
    }
}
