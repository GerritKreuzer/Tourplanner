package at.tourplannerapp.service;


import at.tourplannerapp.Entities.TourItemEntity;
import at.tourplannerapp.Repositories.TourItemRepository;
import at.tourplannerapp.model.TourItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class TourItemServiceImpl implements TourItemService{

    private static final Logger logger = LogManager.getLogger(TourItemServiceImpl.class);

    private final TourItemRepository tourItemRepository;

    public TourItemServiceImpl(TourItemRepository tourItemRepository) {
        this.tourItemRepository = tourItemRepository;
    }

    @Override
    public List<TourItem> getAll() {
        List<TourItemEntity> tourItemEntities = tourItemRepository.findAll();
        return tourItemEntities.stream().map(this::castTourItemEntityToTourItem).toList();
    }

    @Override
    public TourItem create() {
        TourItemEntity tourItemEntity = new TourItemEntity();
        tourItemRepository.saveAndFlush(tourItemEntity);
        tourItemEntity.setName("Tour " + tourItemEntity.getTourId());
        tourItemRepository.saveAndFlush(tourItemEntity);
        return castTourItemEntityToTourItem(tourItemEntity);
    }

    @Override
    public void delete(TourItem tourItem) {
        tourItemRepository.deleteById(tourItem.getTourId());
    }

    @Override
    public void update(TourItem tourItem, Map<String, String> params) {
        tourItem.setName(params.get("Name"));
        tourItem.setDescription(params.get("Description"));
        tourItem.setFromLocation(params.get("FromLocation"));
        tourItem.setToLocation(params.get("ToLocation"));
        tourItem.setTransportationType(params.get("TransportationType"));
        tourItemRepository.saveAndFlush(castTourItemToTourItemEntity(tourItem));
    }

    private TourItem castTourItemEntityToTourItem(TourItemEntity tourItemEntity) {
            return new TourItem(tourItemEntity.getTourId(), tourItemEntity.getName(), tourItemEntity.getDescription(), tourItemEntity.getTransportationType(),
                    tourItemEntity.getDistance(), tourItemEntity.getEstimatedTime(), tourItemEntity.getPathToMap(), tourItemEntity.getUsername(),
                    tourItemEntity.getFromLocation(), tourItemEntity.getToLocation());
    }

    private TourItemEntity castTourItemToTourItemEntity(TourItem tourItem) {
        return new TourItemEntity(tourItem.getTourId(), tourItem.getName(), tourItem.getDescription(), tourItem.getTransportationType(),
                tourItem.getDistance(), tourItem.getEstimatedTime(), tourItem.getPathToMap(), tourItem.getUsername(),
                tourItem.getFromLocation(), tourItem.getToLocation());
    }
}