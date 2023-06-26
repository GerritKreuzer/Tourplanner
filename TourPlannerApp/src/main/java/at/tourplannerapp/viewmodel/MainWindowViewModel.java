package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.PDF.PdfService;
import at.tourplannerapp.service.TourItemService;
import at.tourplannerapp.service.TourLogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainWindowViewModel {
    private SearchBarViewModel searchBarViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourDetailsViewModel tourDetailsViewModel;
    private TourLogOverviewViewModel tourLogOverviewViewModel;
    private TourLogDetailsViewModel tourLogDetailsViewModel;
    private final PdfService pdfService;
    private final TourItemService tourItemService;
    private final TourLogService tourLogService;
    private TourItem tourItem;

    public MainWindowViewModel(SearchBarViewModel searchBarViewModel, TourOverviewViewModel tourOverviewViewModel, TourDetailsViewModel tourDetailsViewModel,
                               TourLogOverviewViewModel tourLogOverviewViewModel, TourLogDetailsViewModel tourLogDetailsViewModel, PdfService pdfService,
                               TourItemService tourItemService, TourLogService tourLogService) {
        this.searchBarViewModel = searchBarViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.tourLogOverviewViewModel = tourLogOverviewViewModel;
        this.tourLogDetailsViewModel = tourLogDetailsViewModel;
        this.pdfService = pdfService;
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

    private void selectTour(TourItem selectedTourItem) {
        tourDetailsViewModel.setTourItem(selectedTourItem);
        tourLogOverviewViewModel.setTourItem(selectedTourItem);
        tourItem = selectedTourItem;
    }

    private void selectTourLog(TourLog selectTourLog) {
        tourLogDetailsViewModel.setTourLog(selectTourLog);
    }


    public void exportPdfTour() {
        pdfService.createReport(tourItem, tourLogService.getAll(tourItem));
    }

    public void exportPdfSummary() {
        Map<TourItem, List<TourLog>> tourMap = new HashMap<TourItem, List<TourLog>>();
        tourItemService.getAll().forEach(tourItem -> {
            tourMap.put(tourItem, tourLogService.getAll(tourItem));
        });
        pdfService.createSummary(tourMap);
    }
}
