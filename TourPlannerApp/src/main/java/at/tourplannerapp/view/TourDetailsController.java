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
    private ComboBox<String> transportTypeComboBox;
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
    @FXML
    private Label fromLocationWeather;
    @FXML
    private Label toLocationWeather;
    @FXML
    private Label weatherDescriptionFromLocation;
    @FXML
    private Label weatherDescriptionToLocation;
    @FXML
    private Label distanceUnitLabel;
    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getMediaDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        transportTypeComboBox.setItems(tourDetailsViewModel.getObservableTransportType());
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
        toLocationTextField.textProperty().bindBidirectional(tourDetailsViewModel.toLocationProperty());
        fromLocationTextField.textProperty().bindBidirectional(tourDetailsViewModel.fromLocationProperty());
        transportTypeComboBox.valueProperty().bindBidirectional(tourDetailsViewModel.transportationTypeProperty());
        distanceLabel.textProperty().bindBidirectional(tourDetailsViewModel.distanceProperty());
        timeLabel.textProperty().bindBidirectional(tourDetailsViewModel.timeProperty());
        saveTourButton.setOnAction(event -> tourDetailsViewModel.onSaveTourButtonClicked());
        validationDetails.textProperty().bindBidirectional(tourDetailsViewModel.validationDetailsProperty());
        tourImageView.imageProperty().bindBidirectional(tourDetailsViewModel.tourImageProperty());
        popularityLabel.textProperty().bindBidirectional(tourDetailsViewModel.popularityProperty());
        childFriendlinessSlider.valueProperty().bindBidirectional(tourDetailsViewModel.childFriendlinessProperty());
        distanceUnitLabel.textProperty().bindBidirectional(tourDetailsViewModel.distanceUnitProperty());
        weatherDescriptionFromLocation.textProperty().bindBidirectional(tourDetailsViewModel.weatherDescriptionFromLocationProperty());
        weatherDescriptionToLocation.textProperty().bindBidirectional(tourDetailsViewModel.weatherDescriptionToLocationProperty());
        fromLocationWeather.textProperty().bindBidirectional(tourDetailsViewModel.weatherFromLocationProperty());
        toLocationWeather.textProperty().bindBidirectional(tourDetailsViewModel.weatherToLocationProperty());

        tourDetailsViewModel.setInvalidDetailsStyle(validationDetailsStyleString -> {
            validationDetails.setStyle(validationDetailsStyleString);
        });
        tourDetailsViewModel.setNameTextFieldStyle(nameTextFieldStyleString -> {
            nameTextField.setStyle(nameTextFieldStyleString);
        });
        tourDetailsViewModel.setFromTextFieldStyle(fromLocationTextFieldStyleString -> {
            fromLocationTextField.setStyle(fromLocationTextFieldStyleString);
        });
        tourDetailsViewModel.setToTextFieldStyle(toLocationTextFieldStyleString -> {
            toLocationTextField.setStyle(toLocationTextFieldStyleString);
        });
        saveTourButton.toFront();
        validationDetails.toFront();
    }
}
