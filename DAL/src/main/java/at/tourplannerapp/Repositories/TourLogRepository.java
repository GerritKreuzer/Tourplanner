package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogRepository extends JpaRepository<TourLog, Integer> {

}
