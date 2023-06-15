package at.tourplannerapp.viewmodel;

import at.tourplannerapp.dto.RouteMatrixRequestBody;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.service.MapService;
import at.tourplannerapp.service.TourItemService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class TourDetailsViewModel {

    private TourItem tourItem;
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty fromLocation = new SimpleStringProperty();
    private final StringProperty toLocation = new SimpleStringProperty();
    private final StringProperty transportationType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty invalidDetails = new SimpleStringProperty();
    private static final String EMPTY_STRING = "";
    private static final String successMessageStyle = "-fx-text-fill: GREEN;";
    private static final String errorMessageStyle = "-fx-text-fill: RED;";

    private static final String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";

    private final TourItemService tourItemService;

    private final MapService mapService;

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

    public StringProperty invalidDetailsProperty() {
        return invalidDetails;
    }

    private Consumer<Boolean> requestRefreshTourItemList;
    private Consumer<String> invalidDetailsStyleString;
    private Consumer<String> nameTextFieldStyleString;

    public void setTourItem(TourItem tourItem) {
        this.tourItem = tourItem;
        if (tourItem == null) {
            name.set(EMPTY_STRING);
            description.set(EMPTY_STRING);
            fromLocation.set(EMPTY_STRING);
            toLocation.set(EMPTY_STRING);
            transportationType.set(EMPTY_STRING);
            return;
        }
        name.setValue(tourItem.getName());
        description.setValue(tourItem.getDescription());
        fromLocation.setValue(tourItem.getFromLocation());
        toLocation.setValue(tourItem.getToLocation());
        transportationType.setValue(tourItem.getTransportationType());
        distance.setValue(tourItem.getDistance() == null ? "" : tourItem.getDistance().toString());
        time.setValue(tourItem.getEstimatedTime() == null ? "" : tourItem.getEstimatedTime().toString());
        invalidDetails.set(EMPTY_STRING);
        nameTextFieldStyleString.accept(EMPTY_STRING);
    }

    public void onSaveTourButtonClicked() {
        if (validInputs()) {
            tourItem.setName(name.get());
            tourItem.setDescription(description.get());
            tourItem.setToLocation(toLocation.get());
            tourItem.setFromLocation(fromLocation.get());
            tourItem.setTransportationType(transportationType.get());
            Double distance = mapService.getDistance(new RouteMatrixRequestBody(new String[]{fromLocation.get(), toLocation.get()}));
            tourItem.setDistance(distance);
            distanceProperty().setValue(distance.toString());
            Long time = mapService.getTime(new RouteMatrixRequestBody(new String[]{fromLocation.get(), toLocation.get()}));
            tourItem.setEstimatedTime(time.intValue());
            timeProperty().setValue(time.toString());
            BufferedImage image = mapService.fetchAndSaveImage();
            // TO - DO: display image
            tourItemService.update(tourItem);
            requestRefreshTourItemList.accept(true);
        }
    }

    public void setRequestRefreshTourItemList(Consumer<Boolean> requestRefreshTourItemList) {
        this.requestRefreshTourItemList = requestRefreshTourItemList;
    }

    public boolean validInputs() {
        if (tourItem == null) {
            invalidDetails.set("Please add a tour!");
            invalidDetailsStyleString.accept(errorMessageStyle);
            return false;
        }
        if (name.get() == null || name.get().isEmpty()) {
            invalidDetails.set("The name field is required!");
            invalidDetailsStyleString.accept(errorMessageStyle);
            nameTextFieldStyleString.accept(errorStyle);
            return false;
        }
        invalidDetails.set("Save successful!");
        invalidDetailsStyleString.accept(successMessageStyle);
        nameTextFieldStyleString.accept(EMPTY_STRING);
        return true;
    }

    public void setInvalidDetailsStyle(Consumer<String> invalidDetailsStyleString) {
        this.invalidDetailsStyleString = invalidDetailsStyleString;
    }

    public void setNameTextFieldStyle(Consumer<String> nameTextFieldStyleString) {
        this.nameTextFieldStyleString = nameTextFieldStyleString;
    }
}
