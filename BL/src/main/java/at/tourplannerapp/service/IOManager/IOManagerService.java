package at.tourplannerapp.service.IOManager;

import at.tourplannerapp.model.TourItemSerializable;

import java.io.File;

public interface IOManagerService {

    void export(TourItemSerializable item);

    TourItemSerializable importTour(File file);
}
