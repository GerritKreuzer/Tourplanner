package at.tourplannerapp.service.IOManager;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;

import java.util.List;

public interface IOManagerService {

    void export(TourItemSerializable item);

    TourItemSerializable importTour(String path);
}
