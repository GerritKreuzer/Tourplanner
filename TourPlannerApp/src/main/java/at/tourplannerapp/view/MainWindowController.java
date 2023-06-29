package at.tourplannerapp.view;

import at.tourplannerapp.Main;
import at.tourplannerapp.viewmodel.MainWindowViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
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
    private ObjectProperty<File> file = new SimpleObjectProperty<>();

    private ObjectProperty<File> directoryFile = new SimpleObjectProperty<>();

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
        mainViewModel.setFileChooser(this::showFileChooser);
        mainViewModel.setDirectoryChooser(this::showDirectoryChooser);
        file.bindBidirectional(mainViewModel.fileProperty());
        directoryFile.bindBidirectional(mainViewModel.directoryFileProperty());
    }

    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    private void showFileChooser(Boolean showFileChooser) {
        if(showFileChooser) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open tour data json");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json Files", "*.json"));
            file.setValue(fileChooser.showOpenDialog(Main.getMainStage()));
        }
    }

    private void showDirectoryChooser(Boolean showDirectoryChooser) {
        if(showDirectoryChooser) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Save to");
            directoryFile.setValue(directoryChooser.showDialog(Main.getMainStage()));
        }
    }
}
