package at.tourplannerapp;

import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.service.iomanager.IOManagerService;
import at.tourplannerapp.service.iomanager.IOManagerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IOManagerServiceTest {

    private static final String fileName = "name1";
    private static final String fileNameWithExtension = fileName + ".json";

    @AfterEach
    void deleteFile() {
        File file = new File(fileNameWithExtension);
        file.delete();
    }

    @Test
    void exportTest(){
        //arrange
        TourItemSerializable item = GenericTourItemSerializable.get(fileName);

        //act
        IOManagerService ioManagerService = new IOManagerServiceImpl();
        ioManagerService.export(new File(""), item);

        File file = new File(fileNameWithExtension);

        //assert
        assertTrue(file.exists());
    }

    @Test
    void importTest(){
        //arrange
        TourItemSerializable item = GenericTourItemSerializable.get(fileName);

        //act
        IOManagerService ioManagerService = new IOManagerServiceImpl();
        ioManagerService.export(new File(""), item);
        TourItemSerializable importItem = ioManagerService.importTour(new File(fileNameWithExtension));

        //assert
        assertEquals(fileName, importItem.getTourItem().getName());
        assertEquals(8, importItem.getTourLogs().size());
    }
}
