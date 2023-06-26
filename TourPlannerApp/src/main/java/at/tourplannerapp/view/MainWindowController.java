package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainWindowController {

    @FXML
    private final MainWindowViewModel mainViewModel;
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
    private MenuItem exportPdfTour;
    @FXML
    private MenuItem exportPdfSummary;

    public MainWindowController(MainWindowViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainViewModel;
    }

    @FXML
    void initialize() {
        exportPdfTour.setOnAction(event -> mainViewModel.exportPdfTour());
        exportPdfSummary.setOnAction(event -> mainViewModel.exportPdfSummary());
    }
}
