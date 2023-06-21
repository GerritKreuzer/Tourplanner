package at.tourplannerapp.repositories;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Integer> {
    List<TourLogEntity> findAllByTourItemEntity(TourItemEntity tourItemEntity);
}

