package at.tourplannerapp.service;

import at.tourplannerapp.dto.RouteResponse;
import at.tourplannerapp.repositories.MapQuestApi;
import javafx.scene.image.Image;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MapServiceImpl implements MapService {
    private final MapQuestApi api;
    private static final String apiKey = "L79tHIt13lDFgMikqzc1o3aEuMh5DxmP";


    public MapServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(MapQuestApi.class);
    }

    public RouteResponse getRouteMatrix(String fromLocation, String toLocation) {
        try {
            Response<RouteResponse> response = api.getRoute(apiKey, fromLocation, toLocation).execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getDistance(String fromLocation, String toLocation) {
        return getRouteMatrix(fromLocation, toLocation).getRoute().getDistance();
    }
    @Override
    public Long getTime(String fromLocation, String toLocation) {
        return getRouteMatrix(fromLocation, toLocation).getRoute().getTime();
    }

    @Override
    public Image fetchImage() {
        Call<ResponseBody> responseBodyCall = api.fetchImage(apiKey, "Boston");
        try {
            byte[] bytes = Objects.requireNonNull(responseBodyCall.execute().body()).bytes();
            return new Image(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
