package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.TourLogDetailsViewModel;
import com.dlsc.gemsfx.TimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TourLogDetailsController {

    private final TourLogDetailsViewModel tourLogDetailsViewModel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Slider difficultySlider;
    @FXML
    private TimePicker totalTimePicker;
    @FXML
    private Slider ratingSlider;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TimePicker timePicker;
    @FXML
    private Button saveTourLogButton;
    @FXML
    private Label validationDetails;

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
        difficultySlider.valueProperty().bindBidirectional(tourLogDetailsViewModel.difficultyProperty());
        totalTimePicker.timeProperty().bindBidirectional(tourLogDetailsViewModel.totalTimeProperty());
        ratingSlider.valueProperty().bindBidirectional(tourLogDetailsViewModel.ratingProperty());
        datePicker.valueProperty().bindBidirectional(tourLogDetailsViewModel.dateProperty());
        timePicker.timeProperty().bindBidirectional(tourLogDetailsViewModel.timeProperty());
        validationDetails.textProperty().bindBidirectional(tourLogDetailsViewModel.validationDetailsProperty());

        saveTourLogButton.setOnAction(event -> tourLogDetailsViewModel.onSaveTourButtonClicked());
        tourLogDetailsViewModel.setInvalidDetailsStyle(validationDetailsStyleString -> {
            validationDetails.setStyle(validationDetailsStyleString);
        });
        tourLogDetailsViewModel.setNameTextFieldStyle(nameTextFieldStyleString -> {
            nameTextField.setStyle(nameTextFieldStyleString);
        });

        datePicker.setConverter(
                new StringConverter<>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });

    }
}

