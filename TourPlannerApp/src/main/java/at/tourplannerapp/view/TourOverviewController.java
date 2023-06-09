package at.tourplannerapp.view;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.viewmodel.TourOverviewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TourOverviewController {
    private final TourOverviewViewModel tourOverviewViewModel;
    @FXML
    private ListView<TourItem> tourItemList;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    public TourOverviewController(TourOverviewViewModel tourOverviewViewModel) {
        this.tourOverviewViewModel = tourOverviewViewModel;
    }

    public TourOverviewViewModel getMediaOverviewViewModel() {
        return tourOverviewViewModel;
    }

    @FXML
    void initialize() {
        tourItemList.setItems(tourOverviewViewModel.getObservableTours());
        tourItemList.getSelectionModel().selectedItemProperty().addListener(tourOverviewViewModel.getChangeListener());
        addButton.setOnAction(event -> tourOverviewViewModel.onAddButtonClicked());
        removeButton.setOnAction(event -> tourOverviewViewModel.onRemoveButtonClicked(tourItemList.getSelectionModel().getSelectedItem()));

        tourOverviewViewModel.setTourSelection(tourItem -> {
            tourItemList.getSelectionModel().select(tourItem);
        });

        tourOverviewViewModel.setRequestRefreshTourItemList(shouldRefresh -> {
            if (shouldRefresh) {
                tourItemList.refresh();
            }
        });
    }
}
