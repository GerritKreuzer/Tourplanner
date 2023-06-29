package at.tourplannerapp;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.service.IOManager.IOManagerService;
import at.tourplannerapp.service.IOManager.IOManagerServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class IOManagerServiceTest {

    @Test
    void exportTest(){
        //arrange
        String name = "name1";
        TourItemSerializable item = createTourItemSerializable(name);

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

    public TourItemSerializable createTourItemSerializable(String name){
        byte[] byteArr = new byte[0];
        TourItem tourItem = new TourItem(1, name, "beschreibung", "bike", 5.5, 7L, byteArr, "wien", "linz");
        List<TourLog> logs = new ArrayList<>();
        logs.add(new TourLog(3, "Tour 1",  LocalDate.of(2020, 1, 8), LocalTime.now(), "comment1", 45, LocalTime.now(), 17));
        for (int i = 0; i < 7; i++) {
            logs.add(new TourLog(i, "Tour 2",LocalDate.of(2020, 1, 8), LocalTime.now(), "comment2", 76, LocalTime.now(), 2));
        }
        return new TourItemSerializable(tourItem, logs);
    }
}
