package at.tourplannerapp.view;

import at.tourplannerapp.config.ApplicationConfigProperties;
import at.tourplannerapp.repositories.TourItemRepository;
import at.tourplannerapp.repositories.TourLogRepository;
import at.tourplannerapp.service.iomanager.IOManagerService;
import at.tourplannerapp.service.iomanager.IOManagerServiceImpl;
import at.tourplannerapp.service.map.MapServiceImpl;
import at.tourplannerapp.service.pdf.PdfService;
import at.tourplannerapp.service.pdf.PdfServiceImpl;
import at.tourplannerapp.service.tour.*;
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

    private ControllerFactory(ConfigurableApplicationContext applicationContext) {
        MapServiceImpl mapService = new MapServiceImpl(applicationContext.getBean(ApplicationConfigProperties.class));
        TourItemService tourItemService = new TourItemServiceImpl(applicationContext.getBean(TourItemRepository.class));
        TourLogService tourLogService = new TourLogServiceImpl(applicationContext.getBean(TourLogRepository.class));
        PdfService pdfService = new PdfServiceImpl(applicationContext.getBean(ApplicationConfigProperties.class));
        IOManagerService ioManagerService = new IOManagerServiceImpl();
        TourSearchService tourSearchService = new TourSearchServiceImpl(tourItemService, tourLogService);
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel(tourItemService);
        tourDetailsViewModel = new TourDetailsViewModel(tourItemService, tourLogService, mapService, applicationContext.getBean(ApplicationConfigProperties.class));
        tourLogDetailsViewModel = new TourLogDetailsViewModel(tourLogService);
        tourLogOverviewViewModel = new TourLogOverviewViewModel(tourLogService);
        mainWindowViewModel = new MainWindowViewModel(searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel,
                tourLogOverviewViewModel, tourLogDetailsViewModel, pdfService, ioManagerService, tourItemService, tourLogService, tourSearchService);
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