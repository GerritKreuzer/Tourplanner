package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.TourLogService;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class TourLogDetailsViewModel {

    private static final String EMPTY_STRING = "";
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
        updateTourLog();
        tourLogService.update(tourLog);
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

}
