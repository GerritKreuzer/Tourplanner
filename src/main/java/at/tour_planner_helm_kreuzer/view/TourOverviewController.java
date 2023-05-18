package at.tour_planner_helm_kreuzer.view;

import at.tour_planner_helm_kreuzer.model.TourItem;
import at.tour_planner_helm_kreuzer.viewmodel.TourOverviewViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TourOverviewController {

    @FXML
    private ListView<TourItem> tourItemList;

    @FXML
    private Button addButton;

    private final TourOverviewViewModel tourOverviewViewModel;

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
    }
}
