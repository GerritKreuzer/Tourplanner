package at.tourplannerapp.service.tour;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.repositories.TourItemRepository;

import java.util.List;
import java.util.Optional;

public class TourItemServiceImpl implements TourItemService {

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
    public TourItem create(TourItem tourItem) {
        TourItemEntity tourItemEntity = new TourItemEntity();
        setTourItemEntityValues(tourItem, tourItemEntity);
        tourItemRepository.saveAndFlush(tourItemEntity);
        return castTourItemEntityToTourItem(tourItemEntity);
    }

    private void setTourItemEntityValues(TourItem tourItem, TourItemEntity tourItemEntity) {
        tourItemEntity.setName(tourItem.getName());
        tourItemEntity.setDescription(tourItem.getDescription());
        tourItemEntity.setTransportationType(tourItem.getTransportationType());
        tourItemEntity.setDistance(tourItem.getDistance());
        tourItemEntity.setEstimatedTime(tourItem.getEstimatedTime());
        tourItemEntity.setMap(tourItem.getMap());
        tourItemEntity.setFromLocation(tourItem.getFromLocation());
        tourItemEntity.setToLocation(tourItem.getToLocation());
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
            setTourItemEntityValues(tourItem, tourItemEntity);
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
                tourItemEntity.getMap(),
                tourItemEntity.getFromLocation(),
                tourItemEntity.getToLocation()
        );
    }
}