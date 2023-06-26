package at.tourplannerapp.service.PDF;

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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfServiceImpl implements PdfService {

    public PdfServiceImpl(){}

    public void createReport(TourItem tourItem, List<TourLog> tourLogs){

        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(tourItem.getName() + ".pdf"));
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
            tourItemTable.addCell(tourItem.getEstimatedTime().toString());
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
            document.add(new Paragraph("Map Image").setFontSize(14).setBold());
            Image mapImage = new Image(ImageDataFactory.create(tourItem.getMap()));
            float imageWidth = 400;
            float imageHeight = 400;
            mapImage.setWidth(imageWidth);
            mapImage.setHeight(imageHeight);
            document.add(mapImage);

            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSummary(TourItem tourItem, List<TourLog> tourLogs){

        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(tourItem.getName() + "-Summary.pdf"));
            Document document = new Document(pdf, PageSize.A4);

            // Create a title paragraph for the TourItem
            Paragraph titleParagraph = new Paragraph(tourItem.getName()+"-Summary")
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

            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAverageRating(List<TourLog> tourLogs) {
        float sum = 0;
        for(TourLog log: tourLogs){
            sum += log.getRating();
        }
        return String.format("%.2f", sum/tourLogs.size());
    }

    private String getAverageDifficulty(List<TourLog> tourLogs) {
        float sum = 0;
        for(TourLog log: tourLogs){
            sum += log.getDifficulty();
        }
        return String.format("%.2f", sum/tourLogs.size());
    }

    private String getAverageTime(List<TourLog> tourLogs) {
        long nanoSum = 0;
        for (TourLog log: tourLogs) {
            nanoSum += log.getTotalTime().toNanoOfDay();
        }
        return LocalTime.ofNanoOfDay(nanoSum / (tourLogs.size())).format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static void main(String[] args) throws IOException {
/*
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\gabri\\Pictures\\Aws.PNG"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] byteArr = baos.toByteArray();

        String d = "rgdgdgdvdihbgsidrghbdirghbrdiöbgsrdtfjugbnsrdgbsdujögsrdl\ngbsrdgbsdrpöiugbsdrpujfgrbsdibgsdighbsgbsdiugbvsdru\ngusdrgbirsuedg34tvz9339ü9gdürgsrdgd";

        TourItem tourItem = new TourItem(1, "tourname", d, "bike", 5.5, 7L, byteArr, "wien", "linz");
        List<TourLog> logs = new ArrayList<>();
        logs.add(new TourLog(3, "Tour 1",  LocalDate.of(2020, 1, 8), LocalTime.now(), "comment1", 45, LocalTime.now(), 17));
        for (int i = 0; i < 100; i++) {
            logs.add(new TourLog(7, "Tour 2",LocalDate.of(2020, 1, 8), LocalTime.now(), "comment2", 76, LocalTime.now(), 2));
        }

        PdfServiceImpl reportService = new PdfServiceImpl();
        reportService.createReport(tourItem, logs);
        reportService.createSummary(tourItem, logs);

 */
    }
}