package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.service.tour.TourItemService;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TourOverviewViewModel {

    private static final Logger LOGGER = LogManager.getLogger(TourOverviewViewModel.class);
    private final ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();
    private final TourItemService tourItemService;
    private final List<SelectionChangedListener> listeners = new ArrayList<>();
    private Consumer<TourItem> tourItemToSelect;
    private Consumer<Boolean> requestRefreshTourItemList;

    public TourOverviewViewModel(TourItemService tourItemService) {
        this.tourItemService = tourItemService;
        setTours(this.tourItemService.getAll());
    }

    public ObservableList<TourItem> getObservableTours() {
        return observableTourItems;
    }

    public ChangeListener<TourItem> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    private void notifyListeners(TourItem newValue) {
        for (var listener : listeners) {
            listener.changeSelection(newValue);
        }
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void setTours(List<TourItem> tourItems) {
        observableTourItems.clear();
        observableTourItems.addAll(tourItems);
    }

    public void onAddButtonClicked() {
        var tour = tourItemService.create();
        observableTourItems.add(tour);
        tourItemToSelect.accept(tour);
        LOGGER.info("add new tour id=[{}], name=[{}]",
                tour.getTourId(),
                tour.getName());
    }

    public void onRemoveButtonClicked(TourItem tourItem) {
        if (tourItem != null) {
            tourItemService.delete(tourItem);
            observableTourItems.remove(tourItem);
            LOGGER.info("remove tour id=[{}], name=[{}]",
                    tourItem.getTourId(),
                    tourItem.getName());
        }
    }

    public void setTourSelection(Consumer<TourItem> tourItemToSelect) {
        this.tourItemToSelect = tourItemToSelect;
    }

    public void updateTourItemList() {
        requestRefreshTourItemList.accept(true);
    }

    public void setRequestRefreshTourItemList(Consumer<Boolean> requestRefreshTourItemList) {
        this.requestRefreshTourItemList = requestRefreshTourItemList;
    }

    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }
}
