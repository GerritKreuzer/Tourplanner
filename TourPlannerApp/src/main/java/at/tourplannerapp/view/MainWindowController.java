package at.tourplannerapp.view;

import at.tourplannerapp.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.util.function.Consumer;

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
    @FXML
    private MenuItem exportTourData;
    @FXML
    private MenuItem importTourData;

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
        exportTourData.setOnAction(event -> mainViewModel.exportTourData());
        importTourData.setOnAction(event -> mainViewModel.importTourData());
        mainViewModel.setAlertInfoMessageString(this::showInformationAlert);
        mainViewModel.setAlertErrorMessageStringInfoMessageString(this::showErrorAlert);
    }

    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
