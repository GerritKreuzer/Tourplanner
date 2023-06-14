package at.tourplannerapp.service;

import at.tourplannerapp.dto.RouteMatrixRequestBody;
import at.tourplannerapp.dto.RouteMatrixResponse;
import at.tourplannerapp.repositories.MapQuestApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class MapService {
    private final MapQuestApi api;
    private static final String apiKey = "L79tHIt13lDFgMikqzc1o3aEuMh5DxmP";


    public MapService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(MapQuestApi.class);
    }

    public void getRouteMatrix(RouteMatrixRequestBody routeMatrixRequestBody) {
        try {
            Response<RouteMatrixResponse> response = api.getRouteMatrix(apiKey, routeMatrixRequestBody).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
