package at.tour_planner_helm_kreuzer.view;

import at.tour_planner_helm_kreuzer.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;

public class MainWindowController {

    @FXML
    private SearchBarController searchBarController;    // injected controller of SearchBar.fxml
    @FXML private TourOverviewController tourOverviewController;    // injected controller of MediaOverview.fxml
    @FXML private TourDetailsController tourDetailsController;    // injected controller of MediaDetails.fxml

    @FXML
    private final MainWindowViewModel mainViewModel;

    public MainWindowController(MainWindowViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainViewModel;
    }

    @FXML void initialize() {
    }
}
