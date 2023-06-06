package at.tourplannerapp.viewmodel;

import at.tourplannerapp.service.TourItemService;
import at.tourplannerapp.service.TourItemServiceImpl;
import at.tourplannerapp.model.TourItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.function.Consumer;

public class TourDetailsViewModel {

    private TourItem tourItem;
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty fromLocation = new SimpleStringProperty();
    private final StringProperty toLocation = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty invalidDetails = new SimpleStringProperty();
    private static final String EMPTY_STRING = "";
    private static final String successMessageStyle = "-fx-text-fill: GREEN;";
    private static final String errorMessageStyle = "-fx-text-fill: RED;";

    private static final String errorStyle = "-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;";

    private final TourItemService tourItemService;
    public TourDetailsViewModel(TourItemService tourItemService)
    {
        this.tourItemService = tourItemService;
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
    public StringProperty invalidDetailsProperty() {
        return invalidDetails;
    }
    private Consumer<Boolean> requestRefreshTourItemList;
    private Consumer<String> invalidDetailsStyleString;
    private Consumer<String> nameTextFieldStyleString;

    public void setTourItem(TourItem tourItem) {
        if(tourItem ==null) {
            name.set(EMPTY_STRING);
            description.set(EMPTY_STRING);
            fromLocation.set(EMPTY_STRING);
            toLocation.set(EMPTY_STRING);
            transportType.set(EMPTY_STRING);
            return;
        }
        this.tourItem = tourItem;
        name.setValue(tourItem.getName());
        description.setValue(tourItem.getDescription());
        fromLocation.setValue(tourItem.getFromLocation());
        toLocation.setValue(tourItem.getToLocation());
        transportType.setValue(tourItem.getTransportType());
        invalidDetails.set(EMPTY_STRING);
        nameTextFieldStyleString.accept(EMPTY_STRING);
    }

    public void onSaveTourButtonClicked() {
        if(validInputs()) {
            tourItemService.saveTour(tourItem, Arrays.asList(name.get(), description.get(), fromLocation.get(), toLocation.get(), transportType.get()));
            requestRefreshTourItemList.accept(true);
        }
    }

    public void setRequestRefreshTourItemList(Consumer<Boolean> requestRefreshTourItemList) {
        this.requestRefreshTourItemList = requestRefreshTourItemList;
    }

    public boolean validInputs() {
        if(tourItem == null) {
            invalidDetails.set("Please add a tour!");
            invalidDetailsStyleString.accept(errorMessageStyle);
            return false;
        }
        if(name.get() == null ||name.get().isEmpty() ) {
            invalidDetails.set("The name field is required!");
            invalidDetailsStyleString.accept(errorMessageStyle);
            nameTextFieldStyleString.accept(errorStyle);
            return false;
        }
        invalidDetails.set("Save successful!");
        invalidDetailsStyleString.accept(successMessageStyle);
        nameTextFieldStyleString.accept(EMPTY_STRING);
        return true;
    }

    public void setInvalidDetailsStyle(Consumer<String> invalidDetailsStyleString) {
        this.invalidDetailsStyleString = invalidDetailsStyleString;
    }

    public void setNameTextFieldStyle(Consumer<String> nameTextFieldStyleString) {
        this.nameTextFieldStyleString = nameTextFieldStyleString;
    }
}
