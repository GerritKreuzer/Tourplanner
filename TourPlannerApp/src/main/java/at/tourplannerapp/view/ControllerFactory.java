package at.tourplannerapp.view;

import at.tourplannerapp.repositories.TourItemRepository;
import at.tourplannerapp.repositories.TourLogRepository;
import at.tourplannerapp.service.*;
import at.tourplannerapp.service.IOManager.IOManagerService;
import at.tourplannerapp.service.IOManager.IOManagerServiceImpl;
import at.tourplannerapp.service.PDF.PdfService;
import at.tourplannerapp.service.PDF.PdfServiceImpl;
import at.tourplannerapp.viewmodel.*;
import org.springframework.context.ConfigurableApplicationContext;

public class ControllerFactory {

    private static ControllerFactory instance;
    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourLogDetailsViewModel tourLogDetailsViewModel;
    private final TourLogOverviewViewModel tourLogOverviewViewModel;

    public ControllerFactory(ConfigurableApplicationContext applicationContext) {
        MapServiceImpl mapService = new MapServiceImpl();
        TourItemService tourItemService = new TourItemServiceImpl(applicationContext.getBean(TourItemRepository.class));
        TourLogService tourLogService = new TourLogServiceImpl(applicationContext.getBean(TourLogRepository.class));
        PdfService pdfService = new PdfServiceImpl();
        IOManagerService ioManagerService = new IOManagerServiceImpl();
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel(tourItemService);
        tourDetailsViewModel = new TourDetailsViewModel(tourItemService, tourLogService, mapService);
        tourLogDetailsViewModel = new TourLogDetailsViewModel(tourLogService);
        tourLogOverviewViewModel = new TourLogOverviewViewModel(tourLogService);
        mainWindowViewModel = new MainWindowViewModel(searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel,
                tourLogOverviewViewModel, tourLogDetailsViewModel, pdfService, ioManagerService, tourItemService, tourLogService);
    }

    public static ControllerFactory getInstance(ConfigurableApplicationContext applicationContext) {
        if (instance == null) {
            instance = new ControllerFactory(applicationContext);
        }
        return instance;
    }

    //
    // Factory-Method Pattern
    //
    public Object create(Class<?> controllerClass) {
        if (controllerClass == MainWindowController.class) {
            return new MainWindowController(mainWindowViewModel);
        } else if (controllerClass == SearchBarController.class) {
            return new SearchBarController(searchBarViewModel);
        } else if (controllerClass == TourDetailsController.class) {
            return new TourDetailsController(tourDetailsViewModel);
        } else if (controllerClass == TourOverviewController.class) {
            return new TourOverviewController(tourOverviewViewModel);
        } else if (controllerClass == TourLogDetailsController.class) {
            return new TourLogDetailsController(tourLogDetailsViewModel);
        } else if (controllerClass == TourLogOverviewController.class) {
            return new TourLogOverviewController(tourLogOverviewViewModel);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }
}