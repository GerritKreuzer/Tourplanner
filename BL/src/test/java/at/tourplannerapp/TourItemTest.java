package at.tourplannerapp;

import at.tourplannerapp.model.TourItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourItemTest {

    @Test
    void tourItemFormatEstimatedTimeTest(){

        //arrange
        TourItem item = GenericTourItemSerializable.get("testname").getTourItem();

        //act
        String result = item.getEstimatedTimeString();

        //assert
        assertEquals("02:18:00", result);
    }

    @Test
    void tourItemFormatEstimatedTimeAndDayTest(){

        //arrange
        TourItem item = new TourItem();
        item.setEstimatedTime(87000L);
        //act
        String result = item.getEstimatedTimeString();

        //assert
        assertEquals("1 Day 00:10:00", result);
    }

    @Test
    void tourItemFormatEstimatedTimeAndDaysTest(){

        //arrange
        TourItem item = new TourItem();
        item.setEstimatedTime(173100L);
        //act
        String result = item.getEstimatedTimeString();

        //assert
        assertEquals("2 Days 00:05:00", result);
    }
}
