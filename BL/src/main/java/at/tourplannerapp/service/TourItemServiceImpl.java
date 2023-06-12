package at.tourplannerapp.service;

import at.tourplannerapp.Entities.TourItemEntity;
import at.tourplannerapp.Repositories.TourItemRepository;
import at.tourplannerapp.model.TourItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public void update(TourItem tourItem) {
        Optional<TourItemEntity> tourItemEntityOptional
                = tourItemRepository.findById(tourItem.getTourId());
        tourItemEntityOptional.ifPresent(tourItemEntity -> {
            tourItemEntity.setName(tourItem.getName());
            tourItemEntity.setDescription(tourItem.getDescription());
            tourItemEntity.setTransportationType(tourItem.getTransportationType());
            tourItemEntity.setToLocation(tourItem.getToLocation());
            tourItemEntity.setFromLocation(tourItem.getFromLocation());
            System.out.println(tourItemEntity.getName());
            System.out.println(tourItemEntity.getDescription());
            System.out.println(tourItemEntity.getTransportationType());

            tourItemRepository.saveAndFlush(tourItemEntity);
        });
    }

    private TourItem castTourItemEntityToTourItem(TourItemEntity tourItemEntity) {
        return new TourItem(
                tourItemEntity.getTourId(),
                tourItemEntity.getName(),
                tourItemEntity.getDescription(),
                tourItemEntity.getTransportationType(),
                tourItemEntity.getDistance(),
                tourItemEntity.getEstimatedTime(),
                tourItemEntity.getPathToMap(),
                tourItemEntity.getUsername(),
                tourItemEntity.getFromLocation(),
                tourItemEntity.getToLocation()
            );
    }
}