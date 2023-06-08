package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.TourItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TourItemRepositoryTest {
    @Autowired
    private TourItemRepository tourItemRepository;

    @Test
    void testInsertion() {
        // given
        var tour = new TourItemEntity("Test", "This is a description", "car", 10.0, 40, "/path", "Gabriel", "Wien", "Graz");

        // when
        tourItemRepository.save(tour);
        tourItemRepository.flush();
        var actualTour = tourItemRepository.findById(tour.getTourId());

        // then
        assertNotNull(actualTour);
        assertTrue(actualTour.isPresent());
        assertEquals(tour, actualTour.get());
    }

    @Test
    void testDeleteByTourId() {
        // given
        var tour = new TourItemEntity("Test", "This is a description", "car", 10.0, 40, "/path", "Gabriel", "Wien", "Graz");

        // when
        tourItemRepository.save(tour);
        tourItemRepository.flush();
        tourItemRepository.deleteByTourId(tour.getTourId());
        var actualTour = tourItemRepository.findById(tour.getTourId());

        // then
        assertEquals(Optional.empty(), actualTour);
        assertFalse(actualTour.isPresent());
    }

}