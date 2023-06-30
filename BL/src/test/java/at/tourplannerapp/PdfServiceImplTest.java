package at.tourplannerapp;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.pdf.PdfService;
import at.tourplannerapp.service.pdf.PdfServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class PdfServiceImplTest {

    @Test
    void createReportTest(){

        //arrange
        String name = "name2";
        TourItemSerializable item = GenericTourItemSerializable.get(name);

        //act
        PdfService pdfService = new PdfServiceImpl();
        pdfService.createReport(new File(""), item.getTourItem(), item.getTourLogs());

        File file = new File(name+".pdf");

        //assert
        assert(file.exists());
    }

    @Test void createSummaryTest(){

        //arrange
        String name = "name2";
        TourItemSerializable item = GenericTourItemSerializable.get(name);
        HashMap<TourItem, List<TourLog>> map = new HashMap<>();
        map.put(item.getTourItem(), item.getTourLogs());

        //act
        PdfService pdfService = new PdfServiceImpl();
        pdfService.createSummary(new File(""), map);

        File file = new File(name+".pdf");

        //assert
        assert(file.exists());
    }
}
