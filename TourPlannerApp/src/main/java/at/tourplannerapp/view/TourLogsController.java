package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.TourLogsViewModel;
import javafx.fxml.FXML;

public class TourLogsController {

    private final TourLogsViewModel tourLogsViewModel;

    public TourLogsController(TourLogsViewModel tourLogsViewModel) {
        this.tourLogsViewModel = tourLogsViewModel;
    }

    public TourLogsViewModel getTourLogsViewModel() {
        return tourLogsViewModel;
    }

    @FXML
    void initialize() {
    }
}
