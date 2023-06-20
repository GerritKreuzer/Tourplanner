package at.tourplannerapp.service;

import at.tourplannerapp.dto.RouteResponse;
import at.tourplannerapp.repositories.MapQuestApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Objects;

public class MapServiceImpl implements MapService {
    private final MapQuestApi api;
    private static final String apiKey = "L79tHIt13lDFgMikqzc1o3aEuMh5DxmP";
    private static final String distanceUnit = "k";

    public MapServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(MapQuestApi.class);
    }

    public RouteResponse getRoute(String transportType, String fromLocation, String toLocation) {
        try {
            Response<RouteResponse> response = api.getRoute(apiKey, distanceUnit, transportType, fromLocation, toLocation).execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getDistance(String transportType, String fromLocation, String toLocation) {
        return getRoute(transportType, fromLocation, toLocation).getRoute().getDistance();
    }
    @Override
    public Long getTime(String transportType, String fromLocation, String toLocation) {
        return getRoute(transportType, fromLocation, toLocation).getRoute().getTime();
    }

    @Override
    public byte[] fetchImageAsByteArray(String fromLocation, String toLocation) {
        Call<ResponseBody> responseBodyCall = api.fetchImage(apiKey, fromLocation, toLocation);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            if(response.body() != null) {
                return Objects.requireNonNull(response.body()).bytes();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
