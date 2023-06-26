package at.tourplannerapp.service.IOManager;

import at.tourplannerapp.model.TourItemSerializable;

public interface IOManagerService {

    void export(TourItemSerializable item);

    TourItemSerializable importTour(String path);
}
