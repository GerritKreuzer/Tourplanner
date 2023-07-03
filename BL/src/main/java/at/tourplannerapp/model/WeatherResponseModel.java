package at.tourplannerapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponseModel {
    private String currentWeatherText;
    private String icon;

    public WeatherResponseModel(String currentWeatherText, String icon) {
        this.currentWeatherText = currentWeatherText;
        this.icon = icon;
    }
}
