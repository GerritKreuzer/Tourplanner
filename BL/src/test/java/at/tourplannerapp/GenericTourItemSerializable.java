package at.tourplannerapp;

import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourItemSerializable;
import at.tourplannerapp.model.TourLog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GenericTourItemSerializable {
    public static TourItemSerializable get(String name){
        byte[] byteArr = new byte[0];
        TourItem tourItem = new TourItem(1, name, "beschreibung", "bike", 5.5, 8280L, byteArr, "wien", "linz");
        List<TourLog> logs = new ArrayList<>();
        logs.add(new TourLog(3, "Tour 1",  LocalDate.of(2020, 1, 8), LocalTime.now(), "comment1", 45, LocalTime.now(), 17));
        for (int i = 0; i < 7; i++) {
            logs.add(new TourLog(i, "Tour 2",LocalDate.of(2020, 1, 8), LocalTime.now(), "comment2", 76, LocalTime.now(), 2));
        }
        return new TourItemSerializable(tourItem, logs);
    }
}
