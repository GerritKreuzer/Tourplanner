package at.tourplannerapp;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.service.PDF.PdfService;
import at.tourplannerapp.service.PDF.PdfServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TourItemTest {

    @Test
    void tourItemFormatEstimatedTimeTest(){

        //arrange
        TourItem item = GenericTourItemSerializable.get("testname").getTourItem();

        //act
        String result = item.getFormattedStringForEstimatedTime();

        //assert
        assert result.equals("02:18:00");
    }
}
