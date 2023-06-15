package at.tourplannerapp.view;

import at.tourplannerapp.repositories.TourItemRepository;
import at.tourplannerapp.service.MapServiceImpl;
import at.tourplannerapp.service.TourItemServiceImpl;
import at.tourplannerapp.viewmodel.*;
import org.springframework.context.ConfigurableApplicationContext;

public class ControllerFactory {

    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourOverviewViewModel tourOverviewViewModel;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourLogsViewModel tourLogsViewModel;

    public ControllerFactory(ConfigurableApplicationContext applicationContext) {
        MapServiceImpl mapService = new MapServiceImpl();
        searchBarViewModel = new SearchBarViewModel();
        tourOverviewViewModel = new TourOverviewViewModel(new TourItemServiceImpl(applicationContext.getBean(TourItemRepository.class)));
        tourDetailsViewModel = new TourDetailsViewModel(new TourItemServiceImpl(applicationContext.getBean(TourItemRepository.class)), mapService);
        tourLogsViewModel = new TourLogsViewModel();
        mainWindowViewModel = new MainWindowViewModel(searchBarViewModel, tourOverviewViewModel, tourDetailsViewModel);
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
        }else if (controllerClass == TourLogsController.class) {
            return new TourLogsController(tourLogsViewModel);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }

    private static ControllerFactory instance;

    public static ControllerFactory getInstance(ConfigurableApplicationContext applicationContext) {
        if (instance == null) {
            instance = new ControllerFactory(applicationContext);
        }
        return instance;
    }
}