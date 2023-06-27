package at.tourplannerapp.service;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import at.tourplannerapp.model.TourItem;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.repositories.TourLogRepository;

import java.util.List;
import java.util.Optional;

public class TourLogServiceImpl implements TourLogService {
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
    public void create(TourItem tourItem, TourLog tourLog) {
        TourLogEntity tourLogEntity = new TourLogEntity(casteTourItemToTourItemEntity(tourItem));
        setTourLogEntityValues(tourLog, tourLogEntity);
        tourLogRepository.saveAndFlush(tourLogEntity);
    }

    private void setTourLogEntityValues(TourLog tourLog, TourLogEntity tourLogEntity) {
        tourLogEntity.setName(tourLog.getName());
        tourLogEntity.setComment(tourLog.getComment());
        tourLogEntity.setDifficulty(tourLog.getDifficulty());
        tourLogEntity.setTotalTime(tourLog.getTotalTime());
        tourLogEntity.setRating(tourLog.getRating());
        tourLogEntity.setDate(tourLog.getDate());
        tourLogEntity.setTime(tourLog.getTime());
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

    @Override
    public void update(TourLog tourLog) {
        Optional<TourLogEntity> tourLogEntityOptional
                = tourLogRepository.findById(tourLog.getTourLogId());
        tourLogEntityOptional.ifPresent(tourLogEntity -> {
            setTourLogEntityValues(tourLog, tourLogEntity);
            tourLogRepository.saveAndFlush(tourLogEntity);
        });
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
                tourLogEntity.getTime(),
                tourLogEntity.getComment(),
                tourLogEntity.getDifficulty(),
                tourLogEntity.getTotalTime(),
                tourLogEntity.getRating()
        );
    }
}
