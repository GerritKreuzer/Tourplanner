package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.IOManager.IOManagerService;
import at.tourplannerapp.service.PDF.PdfService;
import at.tourplannerapp.service.TourItemService;
import at.tourplannerapp.service.TourLogService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    private final PdfService pdfService;
    private final IOManagerService ioManagerService;
    private final TourItemService tourItemService;
    private final TourLogService tourLogService;
    private TourItem tourItem;

    private Consumer<String> alertInfoMessageString;

    private Consumer<String> alertErrorMessageString;

    public MainWindowViewModel(SearchBarViewModel searchBarViewModel, TourOverviewViewModel tourOverviewViewModel, TourDetailsViewModel tourDetailsViewModel,
                               TourLogOverviewViewModel tourLogOverviewViewModel, TourLogDetailsViewModel tourLogDetailsViewModel, PdfService pdfService,
                               IOManagerService ioManagerService, TourItemService tourItemService, TourLogService tourLogService) {
        this.searchBarViewModel = searchBarViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.tourLogOverviewViewModel = tourLogOverviewViewModel;
        this.tourLogDetailsViewModel = tourLogDetailsViewModel;
        this.pdfService = pdfService;
        this.ioManagerService = ioManagerService;
        this.tourItemService = tourItemService;
        this.tourLogService = tourLogService;

        this.tourOverviewViewModel.addSelectionChangedListener(this::selectTour);
        this.tourLogOverviewViewModel.addSelectionChangedListener(this::selectTourLog);

        this.tourDetailsViewModel.setRequestRefreshTourItemList(shouldRefresh -> {
            if (shouldRefresh) {
                this.tourOverviewViewModel.updateTourItemList();
            }
        });

        this.tourLogOverviewViewModel.updateCalculatedAttributes(updateCalculatedAttributes -> {
            if (updateCalculatedAttributes) {
                this.tourDetailsViewModel.setCalculatedProperties();
            }
        });

        this.tourLogDetailsViewModel.updateCalculatedAttributes(updateCalculatedAttributes -> {
            if (updateCalculatedAttributes) {
                this.tourDetailsViewModel.setCalculatedProperties();
            }
        });
    }

    public void setAlertInfoMessageString(Consumer<String> alertInfoMessageString) {
        this.alertInfoMessageString = alertInfoMessageString;
    }

    public void setAlertErrorMessageStringInfoMessageString(Consumer<String> alertErrorMessageString) {
        this.alertErrorMessageString = alertErrorMessageString;
    }

    private void selectTour(TourItem selectedTourItem) {
        tourDetailsViewModel.setTourItem(selectedTourItem);
        tourLogOverviewViewModel.setTourItem(selectedTourItem);
        tourItem = selectedTourItem;
    }

    private void selectTourLog(TourLog selectTourLog) {
        tourLogDetailsViewModel.setTourLog(selectTourLog);
    }


    public void exportPdfTour() {
        if(tourItem == null) {
            alertErrorMessageString.accept("Please select a tour!");
            return;
        }
        pdfService.createReport(tourItem, tourLogService.getAll(tourItem));
        alertInfoMessageString.accept("Pdf was generated successfully");
    }

    public void exportPdfSummary() {
        Map<TourItem, List<TourLog>> tourMap = new HashMap<TourItem, List<TourLog>>();
        if(tourItemService.getAll().isEmpty()) {
            alertErrorMessageString.accept("Please add a tour before generating a pdf summary!");
            return;
        }
        tourItemService.getAll().forEach(tourItem -> {
            tourMap.put(tourItem, tourLogService.getAll(tourItem));
        });
        pdfService.createSummary(tourMap);
        alertInfoMessageString.accept("Pdf summary was generated successfully");
    }

    public void exportTourData() {
        if(tourItem == null) {
            alertErrorMessageString.accept("Please select a tour!");
            return;
        }
        TourItemSerializable tourItemSerializable = new TourItemSerializable(tourItem, tourLogService.getAll(tourItem));
        ioManagerService.export(tourItemSerializable);
        alertInfoMessageString.accept("Tour data was successfully exported");
    }

    public void importTourData() {
    }
}
