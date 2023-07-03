package at.tourplannerapp.repositories;

import at.tourplannerapp.dto.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("forecast.json?days=1&aqi=no&alerts=no")
    Call<WeatherResponse> getWeather(@Query("key") String key, @Query("q") String location);
}
