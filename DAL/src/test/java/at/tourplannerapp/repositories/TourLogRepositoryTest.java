package at.tourplannerapp.repositories;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TourLogRepositoryTest {

    @Autowired
    TourLogRepository tourLogRepository;

    @Test
    void testInsertion() {
        // given
        var tourLog = new TourLogEntity();
/*
        // when
        tourItemRepository.save(tour);
        tourItemRepository.flush();
        var actualTour = tourItemRepository.findById(tour.getTourId());

        // then
        assertNotNull(actualTour);
        assertTrue(actualTour.isPresent());
        assertEquals(tour, actualTour.get());
         */

    }



}
