package at.tourplannerapp.repositories;

import at.tourplannerapp.dto.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("current.json?aqi=no")
    Call<WeatherResponse> getWeather(@Query("key") String key, @Query("q") String location);
}
