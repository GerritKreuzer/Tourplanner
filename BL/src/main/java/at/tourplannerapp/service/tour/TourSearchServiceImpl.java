package at.tourplannerapp.service.tour;

import at.tourplannerapp.model.TourItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TourSearchServiceImpl implements TourSearchService{

    private final TourItemService tourItemService;

    private final TourLogService tourLogService;

    public TourSearchServiceImpl(TourItemService tourItemService, TourLogService tourLogService) {
        this.tourItemService = tourItemService;
        this.tourLogService = tourLogService;
    }

    @Override
    public List<TourItem> findMatchingTours(String searchString) {
        List<TourItem> tours = tourItemService.getAll();
        if (searchString==null || searchString.isEmpty()) {
            return tours;
        }
        return null;
    }
}
