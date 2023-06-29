package at.tourplannerapp;

import at.tourplannerapp.model.RouteResponseModel;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.service.MapService;
import at.tourplannerapp.service.MapServiceImpl;
import org.junit.jupiter.api.Test;

public class MapServiceTest {

    @Test
    void getRouteTest(){

        //arrange
        String transportationType = "car";
        String from = "Vienna";
        String to = "Linz";

        //act
        MapService mapService = new MapServiceImpl();
        RouteResponseModel response = mapService.getRoute(transportationType, from, to);

        //assert
        assert response.getTime() > 5000;
        assert response.getDistance() > 160;
    }

    @Test
    void getImageTest(){

        //arrange
        String from = "Vienna";
        String to = "Linz";

        //act
        MapService mapService = new MapServiceImpl();
        byte[] array = mapService.fetchImageAsByteArray(from, to);

        //assert
        assert array.length != 0;
    }
}
