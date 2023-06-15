package at.tourplannerapp.repositories;

import at.tourplannerapp.dto.RouteMatrixRequestBody;
import at.tourplannerapp.dto.RouteMatrixResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapQuestApi {

    @POST("directions/v2/routematrix")
    Call<RouteMatrixResponse> getRouteMatrix(@Query("key") String key, @Body RouteMatrixRequestBody routeMatrixRequestBody);

    @GET("staticmap/v5/map")
    Call<ResponseBody> fetchImage(@Query("key") String key, @Query("center") String center);
}
