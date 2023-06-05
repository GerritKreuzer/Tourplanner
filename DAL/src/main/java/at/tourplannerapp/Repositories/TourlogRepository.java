package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.Tourlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourlogRepository extends JpaRepository<Tourlog, Integer> {

}
