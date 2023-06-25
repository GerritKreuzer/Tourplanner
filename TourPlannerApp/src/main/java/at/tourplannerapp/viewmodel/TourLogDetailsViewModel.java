package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.TourLogService;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Consumer;

public class TourLogDetailsViewModel {

    private static final String EMPTY_STRING = "";
    private static final String ERROR_MESSAGE_STYLE = "-fx-text-fill: RED;";
    private static final String ERROR_STYLE = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    private static final String SUCCESS_MESSAGE_STYLE = "-fx-text-fill: GREEN;";
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final IntegerProperty difficulty = new SimpleIntegerProperty();
    private final ObjectProperty<LocalTime> totalTime = new SimpleObjectProperty();
    private final IntegerProperty rating = new SimpleIntegerProperty();
    private final StringProperty validationDetails = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> time = new SimpleObjectProperty<>();
    private TourLog tourLog;
    private final TourLogService tourLogService;
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty commentProperty() {
        return comment;
    }
    public IntegerProperty difficultyProperty() {
        return difficulty;
    }
    public ObjectProperty<LocalTime> totalTimeProperty() {
        return totalTime;
    }
    public IntegerProperty ratingProperty() {
        return rating;
    }
    public StringProperty validationDetailsProperty() {
        return validationDetails;
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    public ObjectProperty<LocalTime> timeProperty() { return time; }
    private Consumer<String> validationDetailsStyleString;
    private Consumer<String> nameTextFieldStyleString;

    public TourLogDetailsViewModel(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
    }

    public void setTourLog(TourLog tourLog) {
        this.tourLog = tourLog;
        if (tourLog == null) {
            emptyTourLogProperties();
            return;
        }
        setPropertiesToTourLogValues();
    }

    public void onSaveTourButtonClicked() {
        if (validInputs()) {
            updateTourLog();
            tourLogService.update(tourLog);
        }
    }

    public void setInvalidDetailsStyle(Consumer<String> invalidDetailsStyleString) {
        this.validationDetailsStyleString = invalidDetailsStyleString;
    }

    public void setNameTextFieldStyle(Consumer<String> nameTextFieldStyleString) {
        this.nameTextFieldStyleString = nameTextFieldStyleString;
    }

    private boolean validInputs() {
        if (tourLog == null) {
            setValidationTextAndStyles("Please select a tour log!", ERROR_MESSAGE_STYLE, EMPTY_STRING);
            return false;
        }
        if (name.get() == null || name.get().isEmpty()) {
            setValidationTextAndStyles("The name field is required!", ERROR_MESSAGE_STYLE, ERROR_STYLE);
            return false;
        }
        setValidationTextAndStyles("Save successful!", SUCCESS_MESSAGE_STYLE, EMPTY_STRING);
        return true;
    }

    private void emptyTourLogProperties() {
        name.set(EMPTY_STRING);
        comment.set(EMPTY_STRING);
        difficulty.set(1);
        totalTime.set(null);
        rating.set(1);
        date.set(null);
        time.set(null);
    }

    private void setPropertiesToTourLogValues() {
        name.setValue(tourLog.getName());
        comment.setValue(tourLog.getComment());
        difficulty.setValue(tourLog.getDifficulty());
        totalTime.setValue(tourLog.getTotalTime());
        rating.setValue(tourLog.getRating());
        date.setValue(tourLog.getDate());
        time.setValue(tourLog.getTime());
    }

    private void updateTourLog() {
        tourLog.setName(name.get());
        tourLog.setComment(comment.get());
        tourLog.setDifficulty(difficulty.get());
        tourLog.setTotalTime(totalTime.get());
        tourLog.setRating(rating.get());
        tourLog.setDate(date.get());
        tourLog.setTime(time.getValue());
    }

    private void setValidationTextAndStyles(String invalidDetailsText, String validationDetailsStyleText, String nameTextFieldStyleText) {
        validationDetails.set(invalidDetailsText);
        validationDetailsStyleString.accept(validationDetailsStyleText);
        nameTextFieldStyleString.accept(nameTextFieldStyleText);
    }

}
