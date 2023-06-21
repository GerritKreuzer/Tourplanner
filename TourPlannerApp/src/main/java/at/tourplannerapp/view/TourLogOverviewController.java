package at.tourplannerapp.view;

import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.viewmodel.TourLogOverviewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TourLogOverviewController {

    private final TourLogOverviewViewModel tourLogOverviewViewModel;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<TourLog> tourLogList;
    @FXML
    private Button addButton;

    public TourLogOverviewController(TourLogOverviewViewModel tourLogOverviewViewModel) {
        this.tourLogOverviewViewModel = tourLogOverviewViewModel;
    }

    public TourLogOverviewViewModel getTourLogOverviewViewModel() {
        return tourLogOverviewViewModel;
    }

    @FXML
    void initialize() {
        tourLogList.setItems(tourLogOverviewViewModel.getObservableTourLogs());
        tourLogList.getSelectionModel().selectedItemProperty().addListener(tourLogOverviewViewModel.getChangeListener());
        addButton.setOnAction(event -> tourLogOverviewViewModel.onAddButtonClicked());
        removeButton.setOnAction(event -> tourLogOverviewViewModel.onRemoveButtonClicked(tourLogList.getSelectionModel().getSelectedItem()));

        tourLogOverviewViewModel.setTourLogSelection(tourLog -> {
            tourLogList.getSelectionModel().select(tourLog);
        });
    }
}
