package at.tourplannerapp;

import at.tourplannerapp.config.ApplicationConfigProperties;
import at.tourplannerapp.model.RouteResponseModel;
import at.tourplannerapp.service.map.MapService;
import at.tourplannerapp.service.map.MapServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MapServiceTest {

    private static final String from = "Vienna";
    private static final String to = "Linz";
    private static final String transportationType = "fastest";
    @Autowired
    ApplicationConfigProperties applicationConfigProperties;

    @Test
    void getRouteInMilesTest(){

        //arrange

        //act
        MapService mapService = new MapServiceImpl(applicationConfigProperties);
        RouteResponseModel response = mapService.getRoute(transportationType, from, to);

        //assert
        assertTrue(response.getTime() > 5000);
        assertTrue(response.getDistance() > 100);

        assertEquals(0, (int) response.getStatusCode());
    }

    @Test
    void getRouteInKilometerTest(){

        //arrange
        applicationConfigProperties.setMapquestUnit("k");

        //act
        MapService mapService = new MapServiceImpl(applicationConfigProperties);
        RouteResponseModel response = mapService.getRoute(transportationType, from, to);

        //assert
        assertTrue(response.getTime() > 5000);
        assertTrue(response.getDistance() > 160);


        assertEquals(0, (int) response.getStatusCode());
    }

    @Test
    void getInvalidRouteTest(){

        //arrange

        //act
        MapService mapService = new MapServiceImpl(applicationConfigProperties);
        RouteResponseModel response = mapService.getRoute(transportationType, "asd", to);

        //assert
        assertTrue(response.getStatusCode() != 0);
    }

    @Test
    void getImageTest(){

        //arrange
        MapService mapService = new MapServiceImpl(applicationConfigProperties);
        RouteResponseModel response = mapService.getRoute(transportationType, from, to);

        //act
        byte[] array = mapService.fetchImageAsByteArray(response.getSession());

        //assert
        assertTrue(array.length != 0);
        assertEquals(0, (int) response.getStatusCode());
    }
}
