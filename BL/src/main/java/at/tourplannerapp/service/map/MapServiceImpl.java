package at.tourplannerapp.service.map;

import at.tourplannerapp.config.ApplicationConfigProperties;
import at.tourplannerapp.dto.Info;
import at.tourplannerapp.dto.Route;
import at.tourplannerapp.dto.RouteResponse;
import at.tourplannerapp.model.RouteResponseModel;
import at.tourplannerapp.repositories.MapQuestApi;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Objects;

public class MapServiceImpl implements MapService {

    private static final Logger LOGGER = LogManager.getLogger(MapServiceImpl.class);
    private final String apiKey;
    private final String distanceUnit;
    private MapQuestApi api;

    public MapServiceImpl(ApplicationConfigProperties applicationConfigProperties) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(MapQuestApi.class);
        apiKey = applicationConfigProperties.getMapquestKey();
        distanceUnit = applicationConfigProperties.getMapquestUnit();
    }

    public RouteResponseModel getRoute(String transportType, String fromLocation, String toLocation) {
        try {
            Response<RouteResponse> response = api.getRoute(apiKey, distanceUnit, transportType, fromLocation, toLocation).execute();
            LOGGER.info("MapQuest Api getRoute call response[{}]",
                    response);
            Route route = response.body().getRoute();
            Info info = response.body().getInfo();
            return new RouteResponseModel(route.getDistance(), route.getTime(), route.getSessionId(), info.getStatuscode());
        } catch (IOException e) {
            LOGGER.error("MapQuest Api getRoute exception=[{}]",
                    e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] fetchImageAsByteArray(String session) {
        Call<ResponseBody> responseBodyCall = api.fetchImage(apiKey, session);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            if (response.body() != null) {
                LOGGER.info("MapQuest Api fetchImage call success");
                return Objects.requireNonNull(response.body()).bytes();
            }
            return null;
        } catch (IOException e) {
            LOGGER.error("MapQuest Api fetchImage exception=[{}]",
                    e.toString());
            throw new RuntimeException(e);
        }
    }
}
