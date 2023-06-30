package at.tourplannerapp;

import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.service.iomanager.IOManagerService;
import at.tourplannerapp.service.iomanager.IOManagerServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.File;

public class IOManagerServiceTest {

    @Test
    void exportTest(){
        //arrange
        String name = "name1";
        TourItemSerializable item = GenericTourItemSerializable.get(name);

        //act
        IOManagerService ioManagerService = new IOManagerServiceImpl();
        ioManagerService.export(new File(""), item);

        File file = new File(name+".json");

        //assert
        assert(file.exists());
    }

    @Test
    void importTest(){
        //arrange
        exportTest();

        //act
        IOManagerService ioManagerService = new IOManagerServiceImpl();
        TourItemSerializable item = ioManagerService.importTour(new File("name1.json"));

        //assert
        assert item.getTourItem().getName().equals("name1");
        assert item.getTourLogs().size() == 8;
    }
}
