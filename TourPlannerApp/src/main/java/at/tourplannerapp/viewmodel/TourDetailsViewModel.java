package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.RouteResponseModel;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.MapService;
import at.tourplannerapp.service.TourItemService;
import at.tourplannerapp.service.TourLogService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
    private final StringProperty transportationType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty popularity = new SimpleStringProperty();
    private final StringProperty childFriendliness = new SimpleStringProperty();
    private final StringProperty validationDetails = new SimpleStringProperty();
    private final TourItemService tourItemService;
    private final TourLogService tourLogService;
    private final MapService mapService;
    private TourItem tourItem;
    private Consumer<Boolean> requestRefreshTourItemList;
    private Consumer<String> validationDetailsStyleString;
    private Consumer<String> nameTextFieldStyleString;

    public TourDetailsViewModel(TourItemService tourItemService, TourLogService tourLogService, MapService mapService) {
        this.tourItemService = tourItemService;
        this.tourLogService = tourLogService;
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
    public StringProperty popularityProperty() {
        return popularity;
    }
    public StringProperty childFriendlinessProperty() {
        return childFriendliness;
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
            RouteResponseModel route = mapService.getRoute(transportationType.get(), fromLocation.get(), toLocation.get());
            distanceProperty().setValue(route.getDistance().toString());
            timeProperty().setValue(route.getTime().toString());

            byte[] imageByteArray = mapService.fetchImageAsByteArray(fromLocation.get(), toLocation.get());
            tourImage.set(getImageFromByteArray(imageByteArray));

            updateTour();
            setCalculatedProperties();
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
            setValidationTextAndStyles("Please select a tour!", ERROR_MESSAGE_STYLE, EMPTY_STRING);
            return false;
        }
        if (name.get() == null || name.get().isEmpty()) {
            setValidationTextAndStyles("The name field is required!", ERROR_MESSAGE_STYLE, ERROR_STYLE);
            return false;
        }
        if (name.get().length() > 64) {
            setValidationTextAndStyles("The name field can only be 64 characters long!", ERROR_MESSAGE_STYLE, ERROR_STYLE);
            return false;
        }
        setValidationTextAndStyles("Save successful!", SUCCESS_MESSAGE_STYLE, EMPTY_STRING);
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
        popularity.set(EMPTY_STRING);
        childFriendliness.set(EMPTY_STRING);
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
        time.setValue(tourItem.getEstimatedTime() == null ? "" : getFormattedStringFromEstimatedTime(tourItem.getEstimatedTime()));
        tourImage.setValue(getImageFromByteArray(tourItem.getMap()));
        setCalculatedProperties();
    }

    public void setCalculatedProperties() {
        List<TourLog> tourLogs = tourLogService.getAll(tourItem);
        popularity.setValue(String.valueOf(tourLogs.size()));
        childFriendliness.setValue(String.valueOf(calculateChildFriendliness(tourItem, tourLogs)));
    }

    private Integer calculateChildFriendliness(TourItem tourItem, List<TourLog> tourLogs) {
        double childFriendliness = 0.0;
        childFriendliness += getDistanceFriendliness(tourItem.getDistance());
        childFriendliness += getTotalTimesFriendliness(tourLogs);
        childFriendliness += getDifficultyFriendliness(tourLogs);
        return (int)Math.round(childFriendliness / 3.0);
    }

    private Integer getDistanceFriendliness(Double distance) {
        if(distance == null || distance == 0) {
            return 0;
        }

        int intDistance = (int)Math.round(distance);
        if(intDistance > 100) {
            return 0;
        } else {
            return (10 - (int)Math.round(intDistance/10.0));
        }
    }

    private Integer getTotalTimesFriendliness(List<TourLog> tourLogs) {
        long secSum = 0;
        if(tourLogs.isEmpty()) {
            return 0;
        }

        for (TourLog log: tourLogs) {
            if(log.getTotalTime() == null) {
                continue;
            }
            secSum += log.getTotalTime().toSecondOfDay();
        }
        if(secSum == 0) return 0;
        return (10 - (int)Math.round(secSum / (3600 * 2.4)));
    }

    private Integer getDifficultyFriendliness(List<TourLog> tourLogs) {
        long difficulty = 0;
        if(tourLogs.isEmpty()) {
            return 0;
        }

        for (TourLog log: tourLogs) {
            if(log.getDifficulty() == null) {
                continue;
            }
            difficulty += log.getDifficulty();
        }
        if(difficulty == 0) return 0;
        return (10 - Math.toIntExact(difficulty / tourLogs.size()));
    }

    private String getFormattedStringFromEstimatedTime(Long estimatedTime) {
        int day = (int) TimeUnit.SECONDS.toDays(estimatedTime);
        long hours = TimeUnit.SECONDS.toHours(estimatedTime) - (day *24);
        long minute = TimeUnit.SECONDS.toMinutes(estimatedTime) - (TimeUnit.SECONDS.toHours(estimatedTime)* 60);
        long second = TimeUnit.SECONDS.toSeconds(estimatedTime) - (TimeUnit.SECONDS.toMinutes(estimatedTime) *60);

        StringBuilder str = new StringBuilder();

        if(day != 0) {
            if(day == 1) {
                str.append("1 Day ");
            }
            str.append(day);
            str.append( " Days ");
        }
        str.append(String.format("%02d", hours));
        str.append(":");
        str.append(String.format("%02d", minute));
        str.append(":");
        str.append(String.format("%02d", second));
        return str.toString();
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
