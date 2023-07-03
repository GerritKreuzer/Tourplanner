package at.tourplannerapp.service.tour;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.repositories.TourItemRepository;
import at.tourplannerapp.repositories.TourLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TourSearchServiceImplTest {


    // Autowired doesnt work
    @Autowired
    private TourItemRepository tourItemRepository;
    @Autowired
    private TourLogRepository tourLogRepository;
    @Autowired
    private TourSearchService tourSearchService;

    @BeforeEach
    void InsertionTourItem() {
        TourItemEntity tour = new TourItemEntity("Test", "This is a description", "car", 10.0, 40L, "test".getBytes(), "Wien", "Graz");
        tourItemRepository.saveAndFlush(tour);
        TourItemEntity tour1 = new TourItemEntity("Test2", "This is a description", "car", 10.0, 40L, "test".getBytes(), "Linz", "Graz");
        tourItemRepository.saveAndFlush(tour1);
        TourLogEntity tourLog = new TourLogEntity(tour1);
        tourLog.setName("Test Tourlog");
        tour.setDescription("Find me");
        tourLogRepository.saveAndFlush(tourLog);
    }

    @Test
    void testFindSearchStringInTourItem(){

        //arrange
        String searchString = "Wien";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString);

        //assert
        assertEquals(1, tourItemList.size());
        assertEquals("Test", tourItemList.get(0).getName());
    }

    @Test
    void testFindSearchStringInTourLog(){

        //arrange
        String searchString = "Test Tourlog";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString);

        //assert
        assertEquals(1, tourItemList.size());
        assertEquals("Test2", tourItemList.get(0).getName());
    }

    @Test
    void testFindSearchStringAsSubString(){

        //arrange
        String searchString = "me";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString);

        //assert
        assertEquals(1, tourItemList.size());
        assertEquals("Test2", tourItemList.get(0).getName());
    }

    @Test
    void testSearchStringNotFound(){

        //arrange
        String searchString = "New York";

        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString);

        //assert
        assertEquals(0, tourItemList.size());
    }

    @Test
    void testSearchWithEmptySearchString(){

        //arrange
        String searchString = "";


        //act
        List<TourItem> tourItemList = tourSearchService.findMatchingTours(searchString);

        //assert
        assertEquals(tourItemRepository.findAll().size(), tourItemList.size());
    }
}