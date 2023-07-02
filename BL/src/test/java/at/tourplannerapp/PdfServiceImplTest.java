package at.tourplannerapp;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.pdf.PdfService;
import at.tourplannerapp.service.pdf.PdfServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class PdfServiceImplTest {

    private static final String fileName = "name2";
    private static final String fileNameWithExtension = fileName + ".pdf";

    @Test
    void createReportTest(){

        //arrange
        TourItemSerializable item = GenericTourItemSerializable.get(fileName);

        //act
        PdfService pdfService = new PdfServiceImpl();
        pdfService.createReport(new File(""), item.getTourItem(), item.getTourLogs());

        File file = new File(fileNameWithExtension);

        //assert
        assert(file.exists());
        file.delete();
    }

    @Test void createSummaryTest(){

        //arrange
        TourItemSerializable item = GenericTourItemSerializable.get(fileName);
        HashMap<TourItem, List<TourLog>> map = new HashMap<>();
        map.put(item.getTourItem(), item.getTourLogs());
        map.put(item.getTourItem(), item.getTourLogs());

        //act
        PdfService pdfService = new PdfServiceImpl();
        pdfService.createSummary(new File(""), map);

        File file = new File("summary.pdf");

        //assert
        assert(file.exists());
        file.delete();
    }
}
