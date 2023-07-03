package at.tourplannerapp.repositories;

import at.tourplannerapp.entities.TourItemEntity;
import at.tourplannerapp.entities.TourLogEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TourLogRepositoryTest {

    @Autowired
    TourLogRepository tourLogRepository;

    @Autowired
    TourItemRepository tourItemRepository;

    TourItemEntity tour = new TourItemEntity("Test", "This is a description", "car", 10.0, 40L, "test".getBytes(), "Wien", "Graz");

    @BeforeEach
    void InsertionTourItem() {
        tourItemRepository.save(tour);
        tourItemRepository.flush();
    }

    @Test
    void testInsertion() {
        // given
        TourLogEntity tourLogEntity = new TourLogEntity(tour);

        // when
        tourLogRepository.saveAndFlush(tourLogEntity);
        var actualTourLog = tourLogRepository.findById(tourLogEntity.getTourLogId());

        // then
        assertNotNull(actualTourLog);
        assertTrue(actualTourLog.isPresent());
    }

    @Test
    void testGetAllTourLogsByTourItemEntity() {
        // given
        TourLogEntity tourLogEntity1 = new TourLogEntity(tour);
        TourLogEntity tourLogEntity2 = new TourLogEntity(tour);
        TourLogEntity tourLogEntity3 = new TourLogEntity(tour);

        // when
        tourLogRepository.saveAndFlush(tourLogEntity1);
        tourLogRepository.saveAndFlush(tourLogEntity2);
        tourLogRepository.saveAndFlush(tourLogEntity3);
        var tourLogList = tourLogRepository.findAllByTourItemEntity(tour);

        // then
        assertNotNull(tourLogList);
        assertEquals(3, tourLogList.size());
    }

}
