package at.tour_planner_helm_kreuzer.viewmodel;

import at.tour_planner_helm_kreuzer.model.TourItem;
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

    private ObservableList<TourItem> observableTourItems = FXCollections.observableArrayList();

    public ObservableList<TourItem> getObservableTours() {
        return observableTourItems;
    }
    private Consumer<TourItem> tourItemToSelect;

    public ChangeListener<TourItem> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(TourItem newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<TourItem> tourItems) {
        observableTourItems.clear();
        observableTourItems.addAll(tourItems);
    }

    public void onAddButtonClicked() {
        var tour = new TourItem(1, "1" , 1, "1");
        observableTourItems.add(tour);
        tourItemToSelect.accept(tour);
    }

    public void onRemoveButtonClicked(TourItem tourItems) {
        observableTourItems.remove(tourItems);
    }

    public void setTourSelection(Consumer<TourItem> tourItemToSelect) {
        this.tourItemToSelect = tourItemToSelect;
    }


}
