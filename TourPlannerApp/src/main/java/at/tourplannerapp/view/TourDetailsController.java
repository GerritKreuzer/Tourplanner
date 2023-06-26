package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class TourDetailsController {

    private final TourDetailsViewModel tourDetailsViewModel;
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
    private Label distanceLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Button saveTourButton;
    @FXML
    private Label validationDetails;
    @FXML
    private Label popularityLabel;
    @FXML
    private Slider childFriendlinessSlider;
    @FXML
    private ImageView tourImageView;

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
        transportTypeTextField.textProperty().bindBidirectional(tourDetailsViewModel.transportationTypeProperty());
        distanceLabel.textProperty().bindBidirectional(tourDetailsViewModel.distanceProperty());
        timeLabel.textProperty().bindBidirectional(tourDetailsViewModel.timeProperty());
        saveTourButton.setOnAction(event -> tourDetailsViewModel.onSaveTourButtonClicked());
        validationDetails.textProperty().bindBidirectional(tourDetailsViewModel.validationDetailsProperty());
        tourImageView.imageProperty().bindBidirectional(tourDetailsViewModel.tourImageProperty());
        popularityLabel.textProperty().bindBidirectional(tourDetailsViewModel.popularityProperty());
        childFriendlinessSlider.valueProperty().bindBidirectional(tourDetailsViewModel.childFriendlinessProperty());

        tourDetailsViewModel.setInvalidDetailsStyle(validationDetailsStyleString -> {
            validationDetails.setStyle(validationDetailsStyleString);
        });
        tourDetailsViewModel.setNameTextFieldStyle(nameTextFieldStyleString -> {
            nameTextField.setStyle(nameTextFieldStyleString);
        });
        saveTourButton.toFront();
        validationDetails.toFront();
    }
}
