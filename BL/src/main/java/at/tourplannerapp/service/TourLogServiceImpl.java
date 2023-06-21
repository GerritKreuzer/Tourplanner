package at.tourplannerapp.service;

import at.tourplannerapp.entities.TourLogEntity;
import at.tourplannerapp.model.TourLog;
import at.tourplannerapp.repositories.TourLogRepository;

public class TourLogServiceImpl implements  TourLogService{
    private final TourLogRepository tourLogRepository;

    public TourLogServiceImpl(TourLogRepository tourLogRepository) {
        this.tourLogRepository = tourLogRepository;
    }

    @Override
    public TourLog create() {
        TourLogEntity tourLogEntity = new TourLogEntity();
        tourLogRepository.saveAndFlush(tourLogEntity);
        return castTourLogEntityToTourLog(tourLogEntity);
    }

    @Override
    public void delete(TourLog tourLog) {
        tourLogRepository.deleteById(tourLog.getTourLogId());
    }

    private TourLog castTourLogEntityToTourLog(TourLogEntity tourLogEntity) {
        return new TourLog(
                tourLogEntity.getTourLogId(),
                tourLogEntity.getDate(),
                tourLogEntity.getComment(),
                tourLogEntity.getDifficulty(),
                tourLogEntity.getTotalTime(),
                tourLogEntity.getRating()
        );
    }
}
