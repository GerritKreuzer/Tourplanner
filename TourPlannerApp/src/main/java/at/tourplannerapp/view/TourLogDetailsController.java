package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.TourLogDetailsViewModel;
import com.dlsc.gemsfx.TimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TourLogDetailsController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TimePicker timePicker;
    @FXML
    private TextField difficultyTextField;
    @FXML
    private Button saveTourLogButton;
    @FXML
    private Label validationDetails;
    @FXML
    private TextField totalTimeTextField;
    @FXML
    private TextField ratingTextField;

    private final TourLogDetailsViewModel tourLogDetailsViewModel;

    public TourLogDetailsController(TourLogDetailsViewModel tourLogDetailsViewModel) {
        this.tourLogDetailsViewModel = tourLogDetailsViewModel;
    }

    public TourLogDetailsViewModel getTourLogsViewModel() {
        return tourLogDetailsViewModel;
    }

    @FXML
    void initialize() {
    }
}
