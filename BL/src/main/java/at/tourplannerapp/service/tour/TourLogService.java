package at.tourplannerapp.service.tour;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;

import java.util.List;

public interface TourLogService {
    List<TourLog> getAll(TourItem tourItem);

    TourLog create(TourItem tourItem);
    void create(TourItem tourItem, TourLog tourLog);

    void delete(TourLog tourLog);

    void update(TourLog tourLog);
}
