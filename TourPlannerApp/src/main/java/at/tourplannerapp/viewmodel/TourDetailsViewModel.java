package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.service.MapService;
import at.tourplannerapp.service.TourItemService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.function.Consumer;

public class TourDetailsViewModel {

    private static final String EMPTY_STRING = "";
    private static final String successMessageStyle = "-fx-text-fill: GREEN;";
    private static final String errorMessageStyle = "-fx-text-fill: RED;";
    private static final String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    public final ObjectProperty<Image> tourImage = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty fromLocation = new SimpleStringProperty();
    private final StringProperty toLocation = new SimpleStringProperty();
    private final StringProperty transportationType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty validationDetails = new SimpleStringProperty();
    private final TourItemService tourItemService;
    private final MapService mapService;
    private TourItem tourItem;
    private Consumer<Boolean> requestRefreshTourItemList;
    private Consumer<String> validationDetailsStyleString;
    private Consumer<String> nameTextFieldStyleString;

    public TourDetailsViewModel(TourItemService tourItemService, MapService mapService) {
        this.tourItemService = tourItemService;
        this.mapService = mapService;
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

    public StringProperty transportationTypeProperty() {
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

    public ObjectProperty<Image> tourImageProperty() {
        return tourImage;
    }

    public void setTourItem(TourItem tourItem) {
        this.tourItem = tourItem;
        if (tourItem == null) {
            emptyTourProperties();
            return;
        }
        setPropertiesToTourItemValues();
        validationDetails.set(EMPTY_STRING);
        nameTextFieldStyleString.accept(EMPTY_STRING);
    }

    public void onSaveTourButtonClicked() {
        if (validInputs()) {
            Double distance = mapService.getDistance(transportationType.get(), fromLocation.get(), toLocation.get());
            distanceProperty().setValue(distance.toString());
            Long time = mapService.getTime(transportationType.get(), fromLocation.get(), toLocation.get());
            timeProperty().setValue(time.toString());

            byte[] imageByteArray = mapService.fetchImageAsByteArray(fromLocation.get(), toLocation.get());
            tourImage.set(getImageFromByteArray(imageByteArray));

            updateTour();
            tourItem.setMap(imageByteArray);
            tourItemService.update(tourItem);
            requestRefreshTourItemList.accept(true);
        }
    }

    public void setRequestRefreshTourItemList(Consumer<Boolean> requestRefreshTourItemList) {
        this.requestRefreshTourItemList = requestRefreshTourItemList;
    }

    public boolean validInputs() {
        if (tourItem == null) {
            setValidationTextAndStyles("Please add a tour!", errorMessageStyle, EMPTY_STRING);
            return false;
        }
        if (name.get() == null || name.get().isEmpty()) {
            setValidationTextAndStyles("The name field is required!", errorMessageStyle, errorStyle);
            return false;
        }
        if (name.get().length() > 64) {
            setValidationTextAndStyles("The name field can only be 64 characters long!", errorMessageStyle, errorStyle);
            return false;
        }
        setValidationTextAndStyles("Save successful!", successMessageStyle, EMPTY_STRING);
        return true;
    }

    public void setInvalidDetailsStyle(Consumer<String> invalidDetailsStyleString) {
        this.validationDetailsStyleString = invalidDetailsStyleString;
    }

    public void setNameTextFieldStyle(Consumer<String> nameTextFieldStyleString) {
        this.nameTextFieldStyleString = nameTextFieldStyleString;
    }

    private void emptyTourProperties() {
        name.set(EMPTY_STRING);
        description.set(EMPTY_STRING);
        fromLocation.set(EMPTY_STRING);
        toLocation.set(EMPTY_STRING);
        transportationType.set(EMPTY_STRING);
        distance.set(EMPTY_STRING);
        time.set(EMPTY_STRING);
        tourImage.set(null);
    }

    private void updateTour() {
        tourItem.setName(name.get());
        tourItem.setDescription(description.get());
        tourItem.setToLocation(toLocation.get());
        tourItem.setFromLocation(fromLocation.get());
        tourItem.setTransportationType(transportationType.get());
        tourItem.setDistance(Double.parseDouble(distance.get()));
        tourItem.setEstimatedTime(Long.valueOf(time.get()));
    }

    private void setPropertiesToTourItemValues() {
        name.setValue(tourItem.getName());
        description.setValue(tourItem.getDescription());
        fromLocation.setValue(tourItem.getFromLocation());
        toLocation.setValue(tourItem.getToLocation());
        transportationType.setValue(tourItem.getTransportationType());
        distance.setValue(tourItem.getDistance() == null ? "" : tourItem.getDistance().toString());
        time.setValue(tourItem.getEstimatedTime() == null ? "" : tourItem.getEstimatedTime().toString());
        tourImage.setValue(getImageFromByteArray(tourItem.getMap()));
    }

    private void setValidationTextAndStyles(String invalidDetailsText, String validationDetailsStyleText, String nameTextFieldStyleText) {
        validationDetails.set(invalidDetailsText);
        validationDetailsStyleString.accept(validationDetailsStyleText);
        nameTextFieldStyleString.accept(nameTextFieldStyleText);
    }

    private Image getImageFromByteArray(byte[] imageByteArray) {
        if(imageByteArray == null) {
            return null;
        }
        return new Image(new ByteArrayInputStream(imageByteArray));
    }

}
