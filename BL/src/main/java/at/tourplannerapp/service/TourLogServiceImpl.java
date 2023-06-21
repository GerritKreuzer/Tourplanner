package at.tourplannerapp.service;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.repositories.TourLogRepository;

import java.util.List;

public class TourLogServiceImpl implements TourLogService{
    private final TourLogRepository tourLogRepository;

    public TourLogServiceImpl(TourLogRepository tourLogRepository) {
        this.tourLogRepository = tourLogRepository;
    }

    @Override
    public TourLog create(TourItem tourItem) {
        TourLogEntity tourLogEntity = new TourLogEntity(casteTourItemToTourItemEntity(tourItem));
        tourLogRepository.saveAndFlush(tourLogEntity);
        tourLogEntity.setName("TourLog " + tourLogEntity.getTourLogId());
        tourLogRepository.saveAndFlush(tourLogEntity);
        return castTourLogEntityToTourLog(tourLogEntity);
    }

    @Override
    public void delete(TourLog tourLog) {
        tourLogRepository.deleteById(tourLog.getTourLogId());
    }

    @Override
    public List<TourLog> getAll(TourItem tourItem) {
        List<TourLogEntity> tourLogEntities = tourLogRepository.findAllByTourItemEntity(casteTourItemToTourItemEntity(tourItem));
        return tourLogEntities.stream().map(this::castTourLogEntityToTourLog).toList();
    }

    private TourItemEntity casteTourItemToTourItemEntity(TourItem tourItem) {
        return new TourItemEntity(
                tourItem.getTourId(),
                tourItem.getName(),
                tourItem.getDescription(),
                tourItem.getTransportationType(),
                tourItem.getDistance(),
                tourItem.getEstimatedTime(),
                tourItem.getMap(),
                tourItem.getFromLocation(),
                tourItem.getToLocation()
        );
    }
    private TourLog castTourLogEntityToTourLog(TourLogEntity tourLogEntity) {
        return new TourLog(
                tourLogEntity.getTourLogId(),
                tourLogEntity.getName(),
                tourLogEntity.getDate(),
                tourLogEntity.getComment(),
                tourLogEntity.getDifficulty(),
                tourLogEntity.getTotalTime(),
                tourLogEntity.getRating()
        );
    }
}
