package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Integer> {

}
