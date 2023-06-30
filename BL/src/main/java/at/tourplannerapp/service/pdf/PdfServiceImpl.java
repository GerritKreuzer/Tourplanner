package at.tourplannerapp.service.pdf;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PdfServiceImpl implements PdfService {

    public PdfServiceImpl() {
    }

    public void createReport(File file, TourItem tourItem, List<TourLog> tourLogs) {

        try {
            String filePath = file.getAbsolutePath() + "\\" + tourItem.getName() + ".pdf";
            File pdfFile = new File(filePath);
            System.out.println("Save file path: " + filePath);
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
            tourItemTable.addCell(tourItem.getDescription());
            tourItemTable.addCell("Transportation Type");
            tourItemTable.addCell(tourItem.getTransportationType());
            tourItemTable.addCell("Distance");
            tourItemTable.addCell(tourItem.getDistance().toString());
            tourItemTable.addCell("Estimated Time");
            tourItemTable.addCell(tourItem.getEstimatedTimeString());
            tourItemTable.addCell("Start");
            tourItemTable.addCell(tourItem.getFromLocation());
            tourItemTable.addCell("End");
            tourItemTable.addCell(tourItem.getToLocation());
            // Add more rows as needed for other TourItem attributes

            // Create a table for the TourLogs
            Table tourLogTable = new Table(5);
            tourLogTable.addHeaderCell("Date");
            tourLogTable.addHeaderCell("Comment");
            tourLogTable.addHeaderCell("Difficulty");
            tourLogTable.addHeaderCell("Total Time");
            tourLogTable.addHeaderCell("Rating");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (TourLog tourLog : tourLogs) {
                tourLogTable.addCell((tourLog.getDate().toString()))
                        .addCell(tourLog.getComment())
                        .addCell(tourLog.getDifficulty().toString())
                        .addCell(tourLog.getTotalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .addCell(tourLog.getRating().toString());
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

            }

            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSummary(File file, Map<TourItem, List<TourLog>> tourMap) {

        try {
            String filePath = file.getAbsolutePath() + "\\" + "summary.pdf";
            File pdfFile = new File(filePath);
            System.out.println(filePath);
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

                // Create a table for the TourItem information
                Table tourItemTable = new Table(2);

                tourItemTable.addCell("Time");
                tourItemTable.addCell(getAverageTime(tourLogs));
                tourItemTable.addCell("Difficulty");
                tourItemTable.addCell(getAverageDifficulty(tourLogs));
                tourItemTable.addCell("Rating");
                tourItemTable.addCell(getAverageRating(tourLogs));

                document.add(titleParagraph);
                document.add(subParagraph);
                document.add(tourItemTable);
            });

            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAverageRating(List<TourLog> tourLogs) {
        float sum = 0;
        for (TourLog log : tourLogs) {
            sum += log.getRating();
        }
        return String.format("%.2f", sum / tourLogs.size());
    }

    private String getAverageDifficulty(List<TourLog> tourLogs) {
        float sum = 0;
        for (TourLog log : tourLogs) {
            sum += log.getDifficulty();
        }
        return String.format("%.2f", sum / tourLogs.size());
    }

    private String getAverageTime(List<TourLog> tourLogs) {
        long nanoSum = 0;
        for (TourLog log : tourLogs) {
            nanoSum += log.getTotalTime().toNanoOfDay();
        }
        return LocalTime.ofNanoOfDay(nanoSum / (tourLogs.size())).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}