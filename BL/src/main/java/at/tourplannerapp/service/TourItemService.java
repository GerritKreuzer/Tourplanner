package at.tourplannerapp.service;

import at.tourplannerapp.model.TourItem;

import java.util.List;
import java.util.Map;

public interface TourItemService {
    List<TourItem> getAll();

    TourItem create();

    void delete(TourItem TourItem);

    void update(TourItem tourItem, Map<String, String> params);
}
