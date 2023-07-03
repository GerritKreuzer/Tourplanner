package at.tourplannerapp.service.tour;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;

import java.util.List;
import java.util.Map;

public interface TourSearchService {
    List<TourItem> findMatchingTours(String searchString, Map<TourItem, List<TourLog>> searchMap);
}
