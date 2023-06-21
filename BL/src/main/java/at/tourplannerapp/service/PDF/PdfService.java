package at.tourplannerapp.service.PDF;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.util.List;

public interface PdfService {

    void createReport(TourItem tourItem, List<TourLog> tourLogs);

    void createSummary(TourItem tourItem, List<TourLog> tourLogs);
}
