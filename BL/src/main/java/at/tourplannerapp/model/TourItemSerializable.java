package at.tourplannerapp.model;

import java.util.List;

public class TourItemSerializable {

    TourItem tourItem;
    List<TourLog> tourLogs;

    public TourItemSerializable() {
    }

    public TourItemSerializable(TourItem item, List<TourLog> logs) {
        tourItem = item;
        tourLogs = logs;
    }

    public TourItem getTourItem() {
        return tourItem;
    }

    public List<TourLog> getTourLogs() {
        return tourLogs;
    }
}
