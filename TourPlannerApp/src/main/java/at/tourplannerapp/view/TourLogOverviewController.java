package at.tourplannerapp.view;

import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.viewmodel.TourLogOverviewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TourLogOverviewController {

    private final TourLogOverviewViewModel tourLogOverviewViewModel;
    @FXML
    public Label errorDetails;
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
        errorDetails.textProperty().bindBidirectional(tourLogOverviewViewModel.errorDetailsProperty());

        tourLogOverviewViewModel.setTourLogSelection(tourLog -> {
            tourLogList.getSelectionModel().select(tourLog);
        });

        tourLogOverviewViewModel.setErrorDetailsStyle(errorDetailsHeight -> {
            errorDetails.setMinHeight(errorDetailsHeight);
            errorDetails.setPrefHeight(errorDetailsHeight);
        });
        tourLogOverviewViewModel.setAddButtonStyle(addButtonStyleString -> {
            addButton.setStyle(addButtonStyleString);
        });

        tourLogOverviewViewModel.setRequestRefreshTourLogList(shouldRefresh -> {
            if (shouldRefresh) {
                tourLogList.refresh();
            }
        });
    }
}
