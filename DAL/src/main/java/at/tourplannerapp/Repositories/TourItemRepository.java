package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.TourItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourItemRepository extends JpaRepository<TourItemEntity, Integer> {

}
