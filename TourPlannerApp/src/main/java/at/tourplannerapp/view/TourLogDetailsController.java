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
    private TextField difficultyTextField;
    @FXML
    private TextField totalTimeTextField;
    @FXML
    private TextField ratingTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TimePicker timePicker;
    @FXML
    private Button saveTourLogButton;
    @FXML
    private Label validationDetails;

    private final TourLogDetailsViewModel tourLogDetailsViewModel;

    public TourLogDetailsController(TourLogDetailsViewModel tourLogDetailsViewModel) {
        this.tourLogDetailsViewModel = tourLogDetailsViewModel;
    }

    public TourLogDetailsViewModel getTourLogsViewModel() {
        return tourLogDetailsViewModel;
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(tourLogDetailsViewModel.nameProperty());
        commentTextArea.textProperty().bindBidirectional(tourLogDetailsViewModel.commentProperty());
        difficultyTextField.textProperty().bindBidirectional(tourLogDetailsViewModel.difficultyProperty());
        totalTimeTextField.textProperty().bindBidirectional(tourLogDetailsViewModel.totalTimeProperty());
        ratingTextField.textProperty().bindBidirectional(tourLogDetailsViewModel.ratingProperty());
        datePicker.valueProperty().bindBidirectional(tourLogDetailsViewModel.dateProperty());
        timePicker.timeProperty().bindBidirectional(tourLogDetailsViewModel.timeProperty());
        saveTourLogButton.setOnAction(event -> tourLogDetailsViewModel.onSaveTourButtonClicked());
    }
}
