package at.tourplannerapp;


import at.tourplannerapp.model.TourItem;

import java.util.List;
import java.util.Objects;

public class TourService {

    public void saveTour(TourItem tourItem, List<?> params) {
        try {
            TourItem updatedTourItem = updateTourItem(tourItem, params);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }

        return;
    }

    private TourItem updateTourItem(TourItem tourItem, List<?> params) {
        System.out.println(params);
        tourItem.setName(Objects.requireNonNull(params.get(0), "Name cannot be null").toString());
        tourItem.setDescription((params.get(1)==null)?"":params.get(1).toString());
        return tourItem;
    }
}