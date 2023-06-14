package at.tourplannerapp.repositories;

import at.tourplannerapp.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Integer> {
}
