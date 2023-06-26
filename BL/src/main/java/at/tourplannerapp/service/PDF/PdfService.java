package at.tourplannerapp.service.PDF;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;

import java.util.List;
import java.util.Map;

public interface PdfService {
    void createReport(TourItem tourItem, List<TourLog> tourLogs);

    void createSummary(Map<TourItem, List<TourLog>> tourMap);
}
