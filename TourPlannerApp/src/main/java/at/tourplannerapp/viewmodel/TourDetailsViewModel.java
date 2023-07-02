package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.RouteResponseModel;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.map.MapService;
import at.tourplannerapp.service.tour.TourItemService;
import at.tourplannerapp.service.tour.TourLogService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class TourDetailsViewModel {

    private static final String EMPTY_STRING = "";
    private static final String SUCCESS_MESSAGE_STYLE = "-fx-text-fill: GREEN;";
    private static final String ERROR_MESSAGE_STYLE = "-fx-text-fill: RED;";
    private static final String ERROR_STYLE = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    public final ObjectProperty<Image> tourImage = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty fromLocation = new SimpleStringProperty();
    private final StringProperty toLocation = new SimpleStringProperty();
    private final ObjectProperty<String> transportationType = new SimpleObjectProperty<>();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty popularity = new SimpleStringProperty();
    private final IntegerProperty childFriendliness = new SimpleIntegerProperty();
    private final StringProperty validationDetails = new SimpleStringProperty();
    private final StringProperty distanceUnit = new SimpleStringProperty();
    private final ObservableList<String> observableTransportType = FXCollections.observableArrayList();
    private final TourItemService tourItemService;
    private final TourLogService tourLogService;
    private final MapService mapService;
    private TourItem tourItem;
    private Consumer<Boolean> requestRefreshTourItemList;
    private Consumer<String> validationDetailsStyleString;
    private Consumer<String> nameTextFieldStyleString;
    private Consumer<String> fromLocationTextFieldStyleString;
    private Consumer<String> toLocationTextFieldStyleString;


    public TourDetailsViewModel(TourItemService tourItemService, TourLogService tourLogService, MapService mapService) {
        this.tourItemService = tourItemService;
        this.tourLogService = tourLogService;
        this.mapService = mapService;
        observableTransportType.add("fastest");
        observableTransportType.add("shortest");
        observableTransportType.add("pedestrian");
        observableTransportType.add("bicycle");
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty fromLocationProperty() {
        return fromLocation;
    }

    public StringProperty toLocationProperty() {
        return toLocation;
    }

    public ObjectProperty<String> transportationTypeProperty() {
        return transportationType;
    }

    public StringProperty distanceProperty() {
        return distance;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty validationDetailsProperty() {
        return validationDetails;
    }

    public StringProperty distanceUnitProperty() {
        return distanceUnit;
    }

    public StringProperty popularityProperty() {
        return popularity;
    }

    public IntegerProperty childFriendlinessProperty() {
        return childFriendliness;
    }

    public ObjectProperty<Image> tourImageProperty() {
        return tourImage;
    }
    public ObservableList<String> getObservableTransportType() {
        return observableTransportType;
    }

    public void setTourItem(TourItem tourItem) {
        this.tourItem = tourItem;
        if (tourItem == null) {
            emptyTourProperties();
            return;
        }
        setPropertiesToTourItemValues();
        validationDetails.set(EMPTY_STRING);
        setTextFieldStyles(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    public void onSaveTourButtonClicked() {
        if (validInputs()) {
            RouteResponseModel route = mapService.getRoute(transportationType.get(), fromLocation.get(), toLocation.get());
            if(route.getStatusCode() != 0) {
                setValidationTextAndStyles("The location is invalid!", ERROR_MESSAGE_STYLE);
                setTextFieldStyles(EMPTY_STRING, ERROR_STYLE, ERROR_STYLE);
                return;
            }
            byte[] imageByteArray = mapService.fetchImageAsByteArray(route.getSession());
            tourImage.set(getImageFromByteArray(imageByteArray));
            updateTour(route);
            setCalculatedProperties();
            distance.setValue(tourItem.getDistance().toString());
            distanceUnit.setValue("km");
            time.setValue(tourItem.getEstimatedTimeString());

            tourItem.setMap(imageByteArray);
            tourItemService.update(tourItem);
            requestRefreshTourItemList.accept(true);
            setValidationTextAndStyles("Save successful!", SUCCESS_MESSAGE_STYLE);
            setTextFieldStyles(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
        }
    }

    public void setRequestRefreshTourItemList(Consumer<Boolean> requestRefreshTourItemList) {
        this.requestRefreshTourItemList = requestRefreshTourItemList;
    }

    public boolean validInputs() {
        if (tourItem == null) {
            setValidationTextAndStyles("Please select a tour!", ERROR_MESSAGE_STYLE);
            return false;
        }
        if (name.get() == null || name.get().isEmpty()) {
            setValidationTextAndStyles("The name field is required!", ERROR_MESSAGE_STYLE);
            nameTextFieldStyleString.accept(ERROR_STYLE);
            return false;
        }
        if (name.get().length() > 64) {
            setValidationTextAndStyles("The name field can only be 64 characters long!", ERROR_MESSAGE_STYLE);
            nameTextFieldStyleString.accept(ERROR_STYLE);
            return false;
        }
        return true;
    }

    public void setInvalidDetailsStyle(Consumer<String> invalidDetailsStyleString) {
        this.validationDetailsStyleString = invalidDetailsStyleString;
    }

    public void setNameTextFieldStyle(Consumer<String> nameTextFieldStyleString) {
        this.nameTextFieldStyleString = nameTextFieldStyleString;
    }
    public void setFromTextFieldStyle(Consumer<String> fromLocationTextFieldStyleString) {
        this.fromLocationTextFieldStyleString = fromLocationTextFieldStyleString;
    }
    public void setToTextFieldStyle(Consumer<String> toLocationTextFieldStyleString) {
        this.toLocationTextFieldStyleString = toLocationTextFieldStyleString;
    }

    private void emptyTourProperties() {
        name.set(EMPTY_STRING);
        description.set(EMPTY_STRING);
        fromLocation.set(EMPTY_STRING);
        toLocation.set(EMPTY_STRING);
        transportationType.set(EMPTY_STRING);
        distance.set(EMPTY_STRING);
        distanceUnit.set(EMPTY_STRING);
        time.set(EMPTY_STRING);
        popularity.set(EMPTY_STRING);
        childFriendliness.set(1);
        tourImage.set(null);
    }

    private void updateTour(RouteResponseModel route) {
        tourItem.setName(name.get());
        tourItem.setDescription(description.get());
        tourItem.setToLocation(toLocation.get());
        tourItem.setFromLocation(fromLocation.get());
        tourItem.setTransportationType(transportationType.get());
        tourItem.setDistance(route.getDistance());
        tourItem.setEstimatedTime(route.getTime());
    }

    private void setPropertiesToTourItemValues() {
        name.setValue(tourItem.getName());
        description.setValue(tourItem.getDescription());
        fromLocation.setValue(tourItem.getFromLocation());
        toLocation.setValue(tourItem.getToLocation());
        transportationType.setValue(tourItem.getTransportationType());
        distance.setValue(tourItem.getDistance() == null ? "" : tourItem.getDistance().toString());
        distanceUnit.setValue(tourItem.getDistance() == null ? "" : "km");
        time.setValue(tourItem.getEstimatedTimeString());
        tourImage.setValue(getImageFromByteArray(tourItem.getMap()));
        setCalculatedProperties();
    }

    public void setCalculatedProperties() {
        if(tourItem != null) {
            List<TourLog> tourLogs = tourLogService.getAll(tourItem);
            tourItem.setCalculatedProperties(tourLogs);
            popularity.setValue(tourItem.getPopularity().toString());
            childFriendliness.setValue(tourItem.getChildFriendliness());
        }
    }

    private void setValidationTextAndStyles(String invalidDetailsText, String validationDetailsStyleText) {
        validationDetails.set(invalidDetailsText);
        validationDetailsStyleString.accept(validationDetailsStyleText);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
            validationDetails.set(EMPTY_STRING);
            validationDetailsStyleString.accept(EMPTY_STRING);
        }));
        timeline.play();
    }

    private void setTextFieldStyles(String nameTextFieldStyleText, String fromTextFieldStyleText, String toTextFieldStyleText) {
        nameTextFieldStyleString.accept(nameTextFieldStyleText);
        fromLocationTextFieldStyleString.accept(fromTextFieldStyleText);
        toLocationTextFieldStyleString.accept(toTextFieldStyleText);
    }

    private Image getImageFromByteArray(byte[] imageByteArray) {
        if (imageByteArray == null) {
            return null;
        }
        return new Image(new ByteArrayInputStream(imageByteArray));
    }

}
