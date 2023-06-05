package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Integer> {

}
