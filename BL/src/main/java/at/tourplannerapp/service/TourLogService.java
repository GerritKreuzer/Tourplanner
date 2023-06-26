package at.tourplannerapp.service;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;

import java.util.List;

public interface TourLogService {
    List<TourLog> getAll(TourItem tourItem);

    TourLog create(TourItem tourItem);

    void delete(TourLog tourLog);

    void update(TourLog tourLog);
}
