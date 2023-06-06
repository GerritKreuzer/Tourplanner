package at.tourplannerapp.service;

import at.tourplannerapp.model.TourItem;

import java.util.List;

public interface TourItemService {
    void saveTour(TourItem tourItem, List<?> params);
}
