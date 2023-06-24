package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.TourLogService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TourLogDetailsViewModel {

    private static final String EMPTY_STRING = "";
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final StringProperty difficulty = new SimpleStringProperty();
    private final StringProperty totalTime = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
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
    public StringProperty difficultyProperty() {
        return difficulty;
    }
    public StringProperty totalTimeProperty() {
        return totalTime;
    }
    public StringProperty ratingProperty() {
        return rating;
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
        difficulty.set(EMPTY_STRING);
        totalTime.set(EMPTY_STRING);
        rating.set(EMPTY_STRING);
        date.set(null);
        time.set(null);
    }

    private void setPropertiesToTourLogValues() {
        name.setValue(tourLog.getName());
        comment.setValue(tourLog.getComment());
        difficulty.setValue(tourLog.getDifficulty() == null ? "" : tourLog.getDifficulty().toString());
        totalTime.setValue(tourLog.getTotalTime() == null ? "" : tourLog.getTotalTime().toString());
        rating.setValue(tourLog.getRating() == null ? "" : tourLog.getRating().toString());
        date.setValue(tourLog.getDate());
        time.setValue(tourLog.getTime());
    }

    private void updateTourLog() {
        tourLog.setName(name.get());
        tourLog.setComment(comment.get());
        tourLog.setDifficulty(Objects.equals(difficulty.get(), "") ? null : Integer.valueOf(difficulty.get()));
        tourLog.setTotalTime(Objects.equals(totalTime.get(), "") ? null : Integer.valueOf(totalTime.get()));
        tourLog.setRating(Objects.equals(rating.get(), "") ? null : Integer.valueOf(rating.get()));
        tourLog.setDate(date.get());
        tourLog.setTime(time.getValue());
    }

}
