package at.tourplannerapp.service;

import at.tourplannerapp.model.TourLog;

public interface TourLogService {

    TourLog create();
    void delete(TourLog tourLog);
}
