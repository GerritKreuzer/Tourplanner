package at.tourplannerapp.Repositories;

import at.tourplannerapp.Entities.Tour;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TourRepositoryTest {
    @Autowired
    private TourRepository tourRepository;

    @Test
    void testInsertion() {
        // given
        var tour = new Tour("Test", "This is a description", "car", 10.0, 40, "/path", "Gabriel", "Wien", "Graz");

        // when
        tourRepository.save(tour);
        tourRepository.flush();

        // then
        assertEquals("Test", tour.getName());
        assertEquals("This is a description", tour.getDescription());
        assertEquals("car", tour.getTransportationType());
        assertEquals(10.0, tour.getDistance());
        assertEquals(40, tour.getEstimatedTime());
        assertEquals("/path", tour.getPathToMap());
        assertEquals("Gabriel", tour.getUsername());
        assertEquals("Wien", tour.getFromLocation());
        assertEquals("Graz", tour.getToLocation());
    }

}