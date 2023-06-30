package at.tourplannerapp.service.tour;

import at.tourplannerapp.model.TourItem;

import java.util.List;

public interface TourItemService {
    List<TourItem> getAll();

    TourItem create();
    TourItem create(TourItem tourItem);

    void delete(TourItem tourItem);

    void update(TourItem tourItem);
}
