package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.tour.TourLogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TourLogOverviewViewModel {

    private static final Logger LOGGER = LogManager.getLogger(TourLogOverviewViewModel.class);
    private static final String EMPTY_STRING = "";
    private static final String ERROR_STYLE = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";
    private final ObservableList<TourLog> observableTourLogItems = FXCollections.observableArrayList();
    private final TourLogService tourLogService;
    private final List<SelectionChangedListener> listeners = new ArrayList<>();
    private final StringProperty errorDetails = new SimpleStringProperty();
    private Consumer<TourLog> tourLogToSelect;
    private Consumer<Double> errorDetailsHeight;
    private Consumer<String> addButtonStyleString;
    private Consumer<Boolean> requestRefreshTourLogList;
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
        if (tourItem == null) {
            errorDetails.set("Please select a tour!");
            addButtonStyleString.accept(ERROR_STYLE);
            errorDetailsHeight.accept(30d);
            return;
        }
        var tourLog = tourLogService.create(tourItem);
        tourLog.setDifficulty(1);
        tourLog.setRating(1);
        tourLogService.update(tourLog);
        observableTourLogItems.add(tourLog);
        tourLogToSelect.accept(tourLog);
        LOGGER.info("add new tour log id=[{}], name=[{}] to tour id=[{}], name[{}]",
                tourLog.getTourLogId(),
                tourLog.getName(),
                tourItem.getTourId(),
                tourItem.getName());
    }

    public void onRemoveButtonClicked(TourLog tourLog) {
        if (tourLog != null) {
            tourLogService.delete(tourLog);
            observableTourLogItems.remove(tourLog);
            LOGGER.info("remove tour log id=[{}], name=[{}] from tour id=[{}], name[{}]",
                    tourLog.getTourLogId(),
                    tourLog.getName(),
                    tourItem.getTourId(),
                    tourItem.getName());
        }
    }

    public void setTourLogSelection(Consumer<TourLog> tourLogToSelect) {
        this.tourLogToSelect = tourLogToSelect;
    }

    public void setTourItem(TourItem tourItem) {
        this.tourItem = tourItem;
        if (tourItem != null) {
            setTourLogs(tourLogService.getAll(tourItem));
        } else {
            observableTourLogItems.clear();
        }
    }

    public void setErrorDetailsStyle(Consumer<Double> errorDetailsHeight) {
        this.errorDetailsHeight = errorDetailsHeight;
    }

    public void setAddButtonStyle(Consumer<String> addButtonStyleString) {
        this.addButtonStyleString = addButtonStyleString;
    }

    public void setRequestRefreshTourLogList(Consumer<Boolean> requestRefreshTourLogList) {
        this.requestRefreshTourLogList = requestRefreshTourLogList;
    }

    public void updateTourLogList() {
        requestRefreshTourLogList.accept(true);
    }

    private void removeError() {
        errorDetails.set(EMPTY_STRING);
        addButtonStyleString.accept(EMPTY_STRING);
        errorDetailsHeight.accept(0d);
    }

    public interface SelectionChangedListener {
        void changeSelection(TourLog tourLog);
    }
}
