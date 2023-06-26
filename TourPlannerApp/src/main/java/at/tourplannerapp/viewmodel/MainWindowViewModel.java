package at.tourplannerapp.viewmodel;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;

public class MainWindowViewModel {
    private SearchBarViewModel searchBarViewModel;
    private TourOverviewViewModel tourOverviewViewModel;
    private TourDetailsViewModel tourDetailsViewModel;
    private TourLogOverviewViewModel tourLogOverviewViewModel;
    private TourLogDetailsViewModel tourLogDetailsViewModel;

    public MainWindowViewModel(SearchBarViewModel searchBarViewModel, TourOverviewViewModel tourOverviewViewModel, TourDetailsViewModel tourDetailsViewModel, TourLogOverviewViewModel tourLogOverviewViewModel, TourLogDetailsViewModel tourLogDetailsViewModel) {
        this.searchBarViewModel = searchBarViewModel;
        this.tourOverviewViewModel = tourOverviewViewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;
        this.tourLogOverviewViewModel = tourLogOverviewViewModel;
        this.tourLogDetailsViewModel = tourLogDetailsViewModel;

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
    }

    private void selectTourLog(TourLog selectTourLog) {
        tourLogDetailsViewModel.setTourLog(selectTourLog);
    }


}
