package at.tourplannerapp.repositories;

import at.tourplannerapp.dto.RouteResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapQuestApi {

    @POST("directions/v2/route")
    Call<RouteResponse> getRoute(@Query("key") String key, @Query("unit") String distanceUnit, @Query("routeType") String routeType, @Query("from") String fromLocation, @Query("to") String toLocation);

    @GET("staticmap/v5/map")
    Call<ResponseBody> fetchImage(@Query("key") String key, @Query("start") String fromLocation, @Query("end") String toLocation);
}
