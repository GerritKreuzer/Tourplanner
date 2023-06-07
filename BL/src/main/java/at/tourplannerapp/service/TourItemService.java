package at.tourplannerapp.service;

import at.tourplannerapp.model.TourItem;

import java.util.List;

public interface TourItemService {
    List<TourItem> getAll();

    TourItem create();

    void delete(TourItem TourItem);
}
