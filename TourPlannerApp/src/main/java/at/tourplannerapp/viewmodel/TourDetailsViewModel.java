package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailsViewModel {

    private TourItem tourItem;
    private volatile boolean isInitValue = false;
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty fromLocation = new SimpleStringProperty();
    private final StringProperty toLocation = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();

    private static final String EMPTY_STRING = "";

    public TourDetailsViewModel() {
        //name.addListener( (arg, oldVal, newVal)->updateTourModel());
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
    public StringProperty transportTypeProperty() {
        return transportType;
    }

    public void setTourItem(TourItem tourItem) {
        isInitValue = true;
        if(tourItem ==null) {
            // select the first in the list
            name.setValue(EMPTY_STRING);
            description.set(EMPTY_STRING);
            fromLocation.set(EMPTY_STRING);
            toLocation.set(EMPTY_STRING);
            transportType.set(EMPTY_STRING);
            return;
        }
        this.tourItem = tourItem;
        name.setValue(tourItem.getName());
        description.setValue(tourItem.getDescription());
        fromLocation.setValue(tourItem.getFromLocation().toString());
        toLocation.setValue(tourItem.getToLocation().toString());
        transportType.setValue(tourItem.getTransportType());
        isInitValue = false;
    }

    public void onSaveTourButtonClicked() {
        TourService tourservice = new TourService();
        tourservice.saveTour(tourItem, Arrays.asList(name.get(), description.get()));
    }
    /*
    private void updateTourModel() {
        if( !isInitValue )
            DAL.getInstance().tourDao().update(mediaItemModel, Arrays.asList(mediaItemModel.getId(), name.get(), distance.get(), plannedTime.get()));
    }

     */
}
