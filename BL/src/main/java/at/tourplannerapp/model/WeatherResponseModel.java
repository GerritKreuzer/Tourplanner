package at.tourplannerapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponseModel {
    private String currentWeatherText;
    private String currentTemp;

    public WeatherResponseModel(String currentWeatherText, String currentTemp) {
        this.currentWeatherText = currentWeatherText;
        this.currentTemp = currentTemp;
    }
}
