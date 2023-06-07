package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.service.TourItemService;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TourOverviewViewModel {
    public interface SelectionChangedListener {
        void changeSelection(TourItem tourItem);
    }
    private List<SelectionChangedListener> listeners = new ArrayList<>();
    private final ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();
    public ObservableList<TourItem> getObservableTours() {
        return observableTourItems;
    }
    private Consumer<TourItem> tourItemToSelect;
    private Consumer<Boolean> requestRefreshTourItemList;

    private final TourItemService tourItemService;
    public TourOverviewViewModel(TourItemService tourItemService)
    {
        this.tourItemService = tourItemService;
    }

    public ChangeListener<TourItem> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }
    private void notifyListeners(TourItem newValue) {
        for ( var listener : listeners ) {
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
    }
    public void onRemoveButtonClicked(TourItem tourItem) {
        observableTourItems.remove(tourItem);
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
}
