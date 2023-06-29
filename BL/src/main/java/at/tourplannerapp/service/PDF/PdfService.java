package at.tourplannerapp.service.PDF;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface PdfService {
    void createReport(File file, TourItem tourItem, List<TourLog> tourLogs);

    void createSummary(File file, Map<TourItem, List<TourLog>> tourMap);
}
