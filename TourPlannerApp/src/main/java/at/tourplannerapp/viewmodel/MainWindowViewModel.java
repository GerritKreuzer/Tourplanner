package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.iomanager.IOManagerService;
import at.tourplannerapp.service.pdf.PdfService;
import at.tourplannerapp.service.tour.TourItemService;
import at.tourplannerapp.service.tour.TourLogService;
import at.tourplannerapp.service.tour.TourSearchService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MainWindowViewModel {
    private SearchBarViewModel searchBarViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourDetailsViewModel tourDetailsViewModel;
    private TourLogOverviewViewModel tourLogOverviewViewModel;
    private TourLogDetailsViewModel tourLogDetailsViewModel;

    public final ObjectProperty<File> file = new SimpleObjectProperty<>();
    public final ObjectProperty<File> directoryFile = new SimpleObjectProperty<>();
    private final PdfService pdfService;
    private final IOManagerService ioManagerService;
    private final TourItemService tourItemService;
    private final TourLogService tourLogService;
    private final TourSearchService tourSearchService;
    private TourItem tourItem;
    private Consumer<String> alertInfoMessageString;
    private Consumer<String> alertErrorMessageString;
    private Consumer<Boolean> showFileChooser;
    private Consumer<Boolean> showDirectoryChooser;
    public ObjectProperty<File> fileProperty() {
        return file;
    }
    public ObjectProperty<File> directoryFileProperty() {
        return directoryFile;
    }

    public MainWindowViewModel(SearchBarViewModel searchBarViewModel, TourOverviewViewModel tourOverviewViewModel, TourDetailsViewModel tourDetailsViewModel,
                               TourLogOverviewViewModel tourLogOverviewViewModel, TourLogDetailsViewModel tourLogDetailsViewModel, PdfService pdfService,
                               IOManagerService ioManagerService, TourItemService tourItemService, TourLogService tourLogService, TourSearchService tourSearchService) {
        this.searchBarViewModel = searchBarViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.tourLogOverviewViewModel = tourLogOverviewViewModel;
        this.tourLogDetailsViewModel = tourLogDetailsViewModel;
        this.pdfService = pdfService;
        this.ioManagerService = ioManagerService;
        this.tourItemService = tourItemService;
        this.tourLogService = tourLogService;
        this.tourSearchService = tourSearchService;

        this.tourOverviewViewModel.addSelectionChangedListener(this::selectTour);
        this.tourLogOverviewViewModel.addSelectionChangedListener(this::selectTourLog);
        this.searchBarViewModel.addSearchListener(this::searchTours);

        this.tourDetailsViewModel.setRequestRefreshTourItemList(shouldRefresh -> {
            if (shouldRefresh) {
                this.tourOverviewViewModel.updateTourItemList();
            }
        });
    }

    public void updateCalculatedAttributes() {
        this.tourDetailsViewModel.setCalculatedProperties();
    }

    public void setAlertInfoMessageString(Consumer<String> alertInfoMessageString) {
        this.alertInfoMessageString = alertInfoMessageString;
    }

    public void setAlertErrorMessageStringInfoMessageString(Consumer<String> alertErrorMessageString) {
        this.alertErrorMessageString = alertErrorMessageString;
    }

    public void setFileChooser(Consumer<Boolean> showFileChooser) {
        this.showFileChooser = showFileChooser;
    }

    public void setDirectoryChooser(Consumer<Boolean> showDirectoryChooser) {
        this.showDirectoryChooser = showDirectoryChooser;
    }

    private void selectTour(TourItem selectedTourItem) {
        tourDetailsViewModel.setTourItem(selectedTourItem);
        tourLogOverviewViewModel.setTourItem(selectedTourItem);
        tourItem = selectedTourItem;
    }

    private void selectTourLog(TourLog selectTourLog) {
        tourLogDetailsViewModel.setTourLog(selectTourLog);
    }

    private void searchTours(String searchString) {
        var tours = tourSearchService.findMatchingTours(searchString);
        tourOverviewViewModel.setTours(tours);
    }

    public void exportPdfTour() {
        if(tourItem == null) {
            alertErrorMessageString.accept("Please select a tour!");
            return;
        }
        showDirectoryChooser.accept(true);
        if(directoryFile.get() == null) {
            alertErrorMessageString.accept("Please select a directory!");
        } else {
            pdfService.createReport(directoryFile.get(), tourItem, tourLogService.getAll(tourItem));
            alertInfoMessageString.accept("Pdf was generated successfully");
        }
    }

    public void exportPdfSummary() {
        Map<TourItem, List<TourLog>> tourMap = new HashMap<TourItem, List<TourLog>>();
        if(tourItemService.getAll().isEmpty()) {
            alertErrorMessageString.accept("Please add a tour before generating a pdf summary!");
            return;
        }
        showDirectoryChooser.accept(true);
        if(directoryFile.get() == null) {
            alertErrorMessageString.accept("Please select a directory!");
        } else {
            tourItemService.getAll().forEach(tourItem -> {
                tourMap.put(tourItem, tourLogService.getAll(tourItem));
            });
            pdfService.createSummary(directoryFile.get(), tourMap);
            alertInfoMessageString.accept("Pdf summary was generated successfully");
        }
    }

    public void exportTourData() {
        if(tourItem == null) {
            alertErrorMessageString.accept("Please select a tour!");
            return;
        }
        showDirectoryChooser.accept(true);
        if(directoryFile.get() == null) {
            alertErrorMessageString.accept("Please select a directory!");
        } else {
            TourItemSerializable tourItemSerializable = new TourItemSerializable(tourItem, tourLogService.getAll(tourItem));
            ioManagerService.export(directoryFile.get(), tourItemSerializable);
            alertInfoMessageString.accept("Tour data was successfully exported");
        }
    }

    public void importTourData() {
        showFileChooser.accept(true);
        if(file.get() == null || !(file.get().getName().toUpperCase().endsWith(".JSON"))) {
            alertErrorMessageString.accept("Please select a json file!");
        } else {
            TourItemSerializable tourItemSerializable = ioManagerService.importTour(file.get());
            TourItem importedTourItem = tourItemService.create(tourItemSerializable.getTourItem());
            tourItemSerializable.getTourLogs().forEach(tourLog -> {
                tourLogService.create(importedTourItem, tourLog);
            });
            tourOverviewViewModel.getObservableTours().add(importedTourItem);
            alertInfoMessageString.accept("Tour data was successfully imported");
        }
    }
}
