package at.tourplannerapp;


import at.tourplannerapp.model.TourItem;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class TourService {

    private static final Logger logger = LogManager.getLogger(TourService.class);

    public void saveTour(TourItem tourItem, List<?> params) {
        try {
            updateTourItem(tourItem, params);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void updateTourItem(TourItem tourItem, List<?> params) {
        logger.info(params);
        tourItem.setName(ObjectUtils.requireNonEmpty(params.get(0), "Name cannot be empty").toString());
        tourItem.setDescription((params.get(1)==null)?"":params.get(1).toString());
        tourItem.setFromLocation((params.get(2)==null)?"":params.get(2).toString());
        tourItem.setToLocation((params.get(3)==null)?"":params.get(3).toString());
        tourItem.setTransportType((params.get(4)==null)?"":params.get(4).toString());
    }
}