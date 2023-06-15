package at.tourplannerapp.repositories;

import at.tourplannerapp.dto.RouteMatrixRequestBody;
import at.tourplannerapp.dto.RouteMatrixResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapQuestApi {

    @POST("directions/v2/routematrix")
    Call<RouteMatrixResponse> getRouteMatrix(@Query("key") String key, @Body RouteMatrixRequestBody routeMatrixRequestBody);
}
