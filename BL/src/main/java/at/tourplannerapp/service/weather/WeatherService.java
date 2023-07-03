package at.tourplannerapp.service.weather;

import at.tourplannerapp.model.WeatherResponseModel;

public interface WeatherService {
    WeatherResponseModel getCurrentWeatherForecast(String fromLocation);
}
