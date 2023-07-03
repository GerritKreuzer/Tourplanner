package at.tourplannerapp.service.weather;

import at.tourplannerapp.config.ApplicationConfigProperties;
import at.tourplannerapp.dto.CurrentWeather;
import at.tourplannerapp.dto.WeatherResponse;
import at.tourplannerapp.model.WeatherResponseModel;
import at.tourplannerapp.repositories.WeatherApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class WeatherServiceImpl implements WeatherService{
    private final String apiKey;
    private WeatherApi api;

    private static final Logger LOGGER = LogManager.getLogger(WeatherServiceImpl.class);

    public WeatherServiceImpl(ApplicationConfigProperties applicationConfigProperties) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(WeatherApi.class);
        apiKey = applicationConfigProperties.getWeatherKey();
    }

    public WeatherResponseModel getCurrentWeatherForecast(String fromLocation) {
        try {
            Response<WeatherResponse> response = api.getWeather(apiKey, fromLocation).execute();
            LOGGER.info("Weather Api getRoute call response[{}]",
                    response);
            CurrentWeather currentWeather = response.body().getCurrent();
            return new WeatherResponseModel(currentWeather.getCondition().getText(), currentWeather.getCondition().getIcon());
        } catch (IOException e) {
            LOGGER.error("MapQuest Api getRoute exception=[{}]",
                    e.toString());
            throw new RuntimeException(e);
        }
    }

}
