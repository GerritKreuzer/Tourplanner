package at.tourplannerapp;

import at.tourplannerapp.config.ApplicationConfigProperties;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.pdf.PdfService;
import at.tourplannerapp.service.pdf.PdfServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PdfServiceImplTest {

    private static final String fileName = "name2";
    private static final String fileNameWithExtension = fileName + ".pdf";

    @Autowired
    ApplicationConfigProperties applicationConfigProperties;

    @Test
    void createReportTest(){

        //arrange
        TourItemSerializable item = GenericTourItemSerializable.get(fileName);

        //act
        PdfService pdfService = new PdfServiceImpl(applicationConfigProperties);
        pdfService.createReport(new File(""), item.getTourItem(), item.getTourLogs());

        File file = new File(fileNameWithExtension);

        //assert
        assertTrue(file.exists());
        file.delete();
    }

    @Test void createSummaryTest(){

        //arrange
        TourItemSerializable item = GenericTourItemSerializable.get(fileName);
        HashMap<TourItem, List<TourLog>> map = new HashMap<>();
        map.put(item.getTourItem(), item.getTourLogs());
        map.put(item.getTourItem(), item.getTourLogs());

        //act
        PdfService pdfService = new PdfServiceImpl(applicationConfigProperties);
        pdfService.createSummary(new File(""), map);

        File file = new File("summary.pdf");

        //assert
        assertTrue(file.exists());
        file.delete();
    }
}
