package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.TourLogService;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TourLogOverviewViewModel {
    private final ObservableList<TourLog> observableTourLogItems = FXCollections.observableArrayList();
    private final TourLogService tourLogService;
    private final List<TourLogOverviewViewModel.SelectionChangedListener> listeners = new ArrayList<>();
    private Consumer<TourLog> tourLogToSelect;

    public TourLogOverviewViewModel(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
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
    }

    public void onAddButtonClicked() {
        var tourLog = tourLogService.create();
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

    public interface SelectionChangedListener {
        void changeSelection(TourLog tourLog);
    }
}
