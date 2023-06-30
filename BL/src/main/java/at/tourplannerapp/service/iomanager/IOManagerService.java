package at.tourplannerapp.service.iomanager;

import at.tourplannerapp.model.TourItemSerializable;

import java.io.File;

public interface IOManagerService {

    void export(File file, TourItemSerializable item);

    TourItemSerializable importTour(File file);
}
