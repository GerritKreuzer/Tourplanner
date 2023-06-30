package at.tourplannerapp.service.tour;

import at.tourplannerapp.model.TourItem;

import java.util.List;

public interface TourSearchService {
    List<TourItem> findMatchingTours(String searchString);
}
