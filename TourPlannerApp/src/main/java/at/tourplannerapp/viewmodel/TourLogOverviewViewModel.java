package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.TourLogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TourLogOverviewViewModel {

    private static final String EMPTY_STRING = "";
    private static final String ERROR_STYLE = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    private final ObservableList<TourLog> observableTourLogItems = FXCollections.observableArrayList();
    private final TourLogService tourLogService;
    private final List<TourLogOverviewViewModel.SelectionChangedListener> listeners = new ArrayList<>();
    private final StringProperty errorDetails = new SimpleStringProperty();
    private Consumer<TourLog> tourLogToSelect;
    private Consumer<Double> errorDetailsHeight;
    private Consumer<String> addButtonStyleString;
    private TourItem tourItem;

    public TourLogOverviewViewModel(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
    }

    public StringProperty errorDetailsProperty() {
        return errorDetails;
    }

    public ObservableList<TourLog> getObservableTourLogs() {
        return observableTourLogItems;
    }

    public ChangeListener<TourLog> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    private void notifyListeners(TourLog newValue) {
        for (var listener : listeners) {
            listener.changeSelection(newValue);
        }
    }

    public void addSelectionChangedListener(TourLogOverviewViewModel.SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void setTourLogs(List<TourLog> tourLogs) {
        observableTourLogItems.clear();
        observableTourLogItems.addAll(tourLogs);
        removeError();
    }

    public void onAddButtonClicked() {
        if(tourItem == null) {
            errorDetails.set("Please select a tour!");
            addButtonStyleString.accept(ERROR_STYLE);
            errorDetailsHeight.accept(30d);
            return;
        }
        var tourLog = tourLogService.create(tourItem);
        observableTourLogItems.add(tourLog);
        tourLogToSelect.accept(tourLog);
    }

    public void onRemoveButtonClicked(TourLog tourLog) {
        if (tourLog != null) {
            tourLogService.delete(tourLog);
            observableTourLogItems.remove(tourLog);
        }
    }

    public void setTourLogSelection(Consumer<TourLog> tourLogToSelect) {
        this.tourLogToSelect = tourLogToSelect;
    }

    public void setTourItem(TourItem tourItem) {
        this.tourItem = tourItem;
        setTourLogs(tourLogService.getAll(tourItem));
    }

    public void setErrorDetailsStyle(Consumer<Double> errorDetailsHeight) {
        this.errorDetailsHeight = errorDetailsHeight;
    }

    public void setAddButtonStyle(Consumer<String> addButtonStyleString) {
        this.addButtonStyleString = addButtonStyleString;
    }

    public interface SelectionChangedListener {
        void changeSelection(TourLog tourLog);
    }

    private void removeError() {
        errorDetails.set(EMPTY_STRING);
        addButtonStyleString.accept(EMPTY_STRING);
        errorDetailsHeight.accept(0d);
    }
}
