package at.tourplannerapp.service.pdf;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.map.MapServiceImpl;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PdfServiceImpl implements PdfService {

    private static final Logger LOGGER = LogManager.getLogger(PdfServiceImpl.class);

    public PdfServiceImpl() {
    }

    public void createReport(File file, TourItem tourItem, List<TourLog> tourLogs) {

        try {
            String filePath = file.getAbsolutePath() + "\\" + tourItem.getName() + ".pdf";
            File pdfFile = new File(filePath);
            LOGGER.info("Save [{}] to save path=[{}]",
                    tourItem.getName() + ".pdf",
                    file.getAbsolutePath());
            FileOutputStream outputStream = new FileOutputStream(pdfFile, false);
            PdfDocument pdf = new PdfDocument(new PdfWriter(outputStream));
            Document document = new Document(pdf, PageSize.A4);

            // Create a title paragraph for the TourItem
            Paragraph titleParagraph = new Paragraph(tourItem.getName())
                    .setFontSize(18)
                    .setBold()
                    .setMarginBottom(20);

            // Create a table for the TourItem information
            Table tourItemTable = new Table(2);

            tourItemTable.addCell("Description");
            tourItemTable.addCell(tourItem.getDescription() == null ? "" : tourItem.getDescription());
            tourItemTable.addCell("Transportation Type");
            tourItemTable.addCell(tourItem.getTransportationType() == null ? "" : tourItem.getTransportationType());
            tourItemTable.addCell("Distance");
            tourItemTable.addCell(tourItem.getDistance().toString() == null ? "" : tourItem.getDistance().toString());
            tourItemTable.addCell("Estimated Time");
            tourItemTable.addCell(tourItem.getEstimatedTimeString() == null ? "" : tourItem.getEstimatedTimeString());
            tourItemTable.addCell("Start");
            tourItemTable.addCell(tourItem.getFromLocation() == null ? "" : tourItem.getFromLocation());
            tourItemTable.addCell("End");
            tourItemTable.addCell(tourItem.getToLocation() == null ? "" : tourItem.getToLocation());
            // Add more rows as needed for other TourItem attributes

            // Create a table for the TourLogs
            Table tourLogTable = new Table(5);
            tourLogTable.addHeaderCell("Date");
            tourLogTable.addHeaderCell("Comment");
            tourLogTable.addHeaderCell("Difficulty");
            tourLogTable.addHeaderCell("Total Time");
            tourLogTable.addHeaderCell("Rating");

            for (TourLog tourLog : tourLogs) {
                tourLogTable
                        .addCell((tourLog.getDate() == null ? "" : tourLog.getDate().toString()))
                        .addCell(tourLog.getComment() == null ? "" : tourLog.getComment())
                        .addCell(tourLog.getDifficulty() == null ? "" : tourLog.getDifficulty().toString())
                        .addCell(tourLog.getTotalTime() == null ? "" : tourLog.getTotalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .addCell(tourLog.getRating() == null ? "" : tourLog.getRating().toString());
            }

            // Add the title, TourItem table, and TourLog table to the document
            document.add(titleParagraph);
            document.add(tourItemTable);
            document.add(new Paragraph("Tour Logs").setFontSize(14).setBold());
            document.add(tourLogTable);

            // Add the image
            try{
                document.add(new Paragraph("Map Image").setFontSize(14).setBold());
                Image mapImage = new Image(ImageDataFactory.create(tourItem.getMap()));
                float imageWidth = 400;
                float imageHeight = 400;
                mapImage.setWidth(imageWidth);
                mapImage.setHeight(imageHeight);
                document.add(mapImage);
            }catch (Exception e){
                LOGGER.error("Export pdf for tour id=[{}], name=[{}] image exception=[{}]",
                        tourItem.getTourId(),
                        tourItem.getName(),
                        e.toString());
            }

            document.close();

        } catch (IOException e) {
            LOGGER.error("Export pdf for tour id=[{}], name=[{}] exception=[{}]",
                    tourItem.getTourId(),
                    tourItem.getName(),
                    e.toString());
            throw new RuntimeException(e);
        }
    }

    public void createSummary(File file, Map<TourItem, List<TourLog>> tourMap) {

        try {
            String filePath = file.getAbsolutePath() + "\\" + "summary.pdf";
            File pdfFile = new File(filePath);
            LOGGER.info("Save summary.pdf to save path=[{}]",
                    file.getAbsolutePath());
            FileOutputStream outputStream = new FileOutputStream(pdfFile, false);
            PdfDocument pdf = new PdfDocument(new PdfWriter(outputStream));
            Document document = new Document(pdf, PageSize.A4);

            tourMap.forEach((tourItem, tourLogs) -> {
                // Create a title paragraph for the TourItem
                Paragraph titleParagraph = new Paragraph(tourItem.getName() + "-Summary")
                        .setFontSize(18)
                        .setBold()
                        .setMarginBottom(20);

                Paragraph subParagraph = new Paragraph("Average Values of all Tourlogs")
                        .setFontSize(14)
                        .setBold();

                document.add(titleParagraph);
                document.add(subParagraph);

                if(tourLogs.isEmpty()) {
                    Paragraph tourLogsEmpty = new Paragraph("The Tourlogs are empty!")
                            .setFontSize(14);
                    document.add(tourLogsEmpty);
                } else {
                    // Create a table for the TourItem information
                    Table tourItemTable = new Table(2);

                    tourItemTable.addCell("Time");
                    tourItemTable.addCell(getAverageTime(tourLogs));
                    tourItemTable.addCell("Difficulty");
                    tourItemTable.addCell(getAverageDifficulty(tourLogs));
                    tourItemTable.addCell("Rating");
                    tourItemTable.addCell(getAverageRating(tourLogs));


                    document.add(tourItemTable);
                }
            });

            document.close();

        } catch (IOException e) {
            LOGGER.error("Export pdf summary exception=[{}]",
                    e.toString());
            throw new RuntimeException(e);
        }
    }

    private String getAverageRating(List<TourLog> tourLogs) {
        float sum = 0;
        for (TourLog log : tourLogs) {
            if(log.getRating() == null) continue;
            sum += log.getRating();
        }
        return String.format("%.2f", sum / tourLogs.size());
    }

    private String getAverageDifficulty(List<TourLog> tourLogs) {
        float sum = 0;
        for (TourLog log : tourLogs) {
            if(log.getDifficulty() == null) continue;
            sum += log.getDifficulty();
        }
        return String.format("%.2f", sum / tourLogs.size());
    }

    private String getAverageTime(List<TourLog> tourLogs) {
        long nanoSum = 0;
        for (TourLog log : tourLogs) {
            if(log.getTotalTime() == null) continue;
            nanoSum += log.getTotalTime().toNanoOfDay();
        }
        return LocalTime.ofNanoOfDay(nanoSum / (tourLogs.size())).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}