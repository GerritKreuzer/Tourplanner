package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;

public class MainWindowController {

    @FXML
    private SearchBarController searchBarController;

    @FXML
    private TourOverviewController tourOverviewController;

    @FXML
    private TourDetailsController tourDetailsController;

    @FXML
    private TourLogOverviewController tourLogOverviewController;

    @FXML
    private TourLogDetailsController tourLogDetailsController;

    @FXML
    private final MainWindowViewModel mainViewModel;

    public MainWindowController(MainWindowViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainViewModel;
    }

    @FXML
    void initialize() {
    }
}
