package at.tourplannerapp.service.map;

import at.tourplannerapp.dto.Info;
import at.tourplannerapp.dto.Route;
import at.tourplannerapp.dto.RouteResponse;
import at.tourplannerapp.model.RouteResponseModel;
import at.tourplannerapp.repositories.MapQuestApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Objects;

public class MapServiceImpl implements MapService {
    private static final String apiKey = "L79tHIt13lDFgMikqzc1o3aEuMh5DxmP";
    private static final String distanceUnit = "k";
    private final MapQuestApi api;

    public MapServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(MapQuestApi.class);
    }

    public RouteResponseModel getRoute(String transportType, String fromLocation, String toLocation) {
        try {
            Response<RouteResponse> response = api.getRoute(apiKey, distanceUnit, transportType, fromLocation, toLocation).execute();
            System.out.println(response);
            Route route = response.body().getRoute();
            Info info = response.body().getInfo();
            return new RouteResponseModel(route.getDistance(), route.getTime(), route.getSessionId(), info.getStatuscode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] fetchImageAsByteArray(String session) {
        Call<ResponseBody> responseBodyCall = api.fetchImage(apiKey, session);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            if (response.body() != null) {
                return Objects.requireNonNull(response.body()).bytes();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
