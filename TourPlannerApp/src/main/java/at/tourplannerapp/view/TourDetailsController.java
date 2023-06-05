package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TourDetailsController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField fromLocationTextField;

    @FXML
    private TextField toLocationTextField;

    @FXML
    private TextField transportTypeTextField;

    @FXML
    private Button saveTourButton;

    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getMediaDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
        toLocationTextField.textProperty().bindBidirectional(tourDetailsViewModel.toLocationProperty());
        fromLocationTextField.textProperty().bindBidirectional(tourDetailsViewModel.fromLocationProperty());
        transportTypeTextField.textProperty().bindBidirectional(tourDetailsViewModel.transportTypeProperty());
        saveTourButton.setOnAction(event -> tourDetailsViewModel.onSaveTourButtonClicked());
    }
}
