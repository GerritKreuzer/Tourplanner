package at.tourplannerapp.repositories;

import at.tourplannerapp.entities.TourItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourItemRepository extends JpaRepository<TourItemEntity, Integer> {
}
