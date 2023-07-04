package at.tourplannerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    private WeatherCondition condition;
    private Double temp_c;
}
