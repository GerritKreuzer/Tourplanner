package at.tourplannerapp.service;

import at.tourplannerapp.dto.RouteMatrixRequestBody;
import at.tourplannerapp.dto.RouteMatrixResponse;
import at.tourplannerapp.repositories.MapQuestApi;
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

    public RouteMatrixResponse getRouteMatrix(RouteMatrixRequestBody routeMatrixRequestBody) {
        try {
            Response<RouteMatrixResponse> response = api.getRouteMatrix(apiKey, routeMatrixRequestBody).execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getDistance(RouteMatrixRequestBody routeMatrixRequestBody) {
        double[] distanceArray = getRouteMatrix(routeMatrixRequestBody).getDistance();
        return distanceArray[distanceArray.length-1];
    }
    @Override
    public Long getTime(RouteMatrixRequestBody routeMatrixRequestBody) {
        long[] timeArray = getRouteMatrix(routeMatrixRequestBody).getTime();
        return timeArray[timeArray.length-1];
    }

    @Override
    public BufferedImage fetchAndSaveImage() {
        Call<ResponseBody> responseBodyCall = api.fetchImage(apiKey, "Boston");
        try {
            byte[] bytes = Objects.requireNonNull(responseBodyCall.execute().body()).bytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(bais);
            return image;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
