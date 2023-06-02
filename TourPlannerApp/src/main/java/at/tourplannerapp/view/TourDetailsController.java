package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TourDetailsController {
    @FXML
    public TextField nameTextField;

    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getMediaDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
    }
}
