package at.tourplannerapp.service.tour;

import at.tourplannerapp.GenericTourItemSerializable;
import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TourSearchServiceImplTest {

    private TourSearchService tourSearchService = new TourSearchServiceImpl();

    private Map<TourItem, List<TourLog>> searchMap;

    @BeforeEach
    void InsertionTourItem() {
        TourItem tourItem1 = new TourItem(1, "Test1", "beschreibung", "bike", 5.5, 8280L, "test".getBytes(), "Wien", "Linz");
        TourItem tourItem2 = new TourItem(2, "Test2", "beschreibung", "bike", 5.5, 8280L, "test".getBytes(), "Graz", "linz");


        List<TourLog> logs = new ArrayList<>();
        logs.add(new TourLog(3, "Test Tourlog",  LocalDate.of(2020, 1, 8), LocalTime.now(), "Find me", 45, LocalTime.now(), 17));

        searchMap = new HashMap<>();
        searchMap.put(tourItem1, Collections.emptyList());
        searchMap.put(tourItem2, logs);
    }

    @Test
    void testFindSearchStringInTourItem(){

        //arrange
        String searchString = "Wien";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString, searchMap);

        //assert
        assertEquals(1, tourItemList.size());
        assertEquals("Test1", tourItemList.get(0).getName());
    }

    @Test
    void testFindSearchStringInTourLog(){

        //arrange
        String searchString = "Test Tourlog";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString, searchMap);

        //assert
        assertEquals(1, tourItemList.size());
        assertEquals("Test2", tourItemList.get(0).getName());
    }

    @Test
    void testFindSearchStringAsSubString(){

        //arrange
        String searchString = "me";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString, searchMap);

        //assert
        assertEquals(1, tourItemList.size());
        assertEquals("Test2", tourItemList.get(0).getName());
    }

    @Test
    void testSearchStringNotFound(){

        //arrange
        String searchString = "New York";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString, searchMap);

        //assert
        assertEquals(0, tourItemList.size());
    }

    @Test
    void testSearchWithEmptySearchString(){

        //arrange
        String searchString = "";


        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString, searchMap);

        //assert
        assertEquals(searchMap.keySet().size(), tourItemList.size());
    }
}