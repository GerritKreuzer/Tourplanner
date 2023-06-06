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
        var tour = new Tour();
        tour.setName("Test");
        tour.setDescription("This is a description");

        // when
        tourRepository.save(tour);
        tourRepository.flush();

        // then
        assertEquals("Test", tour.getName());
        assertEquals("This is a description", tour.getDescription());
    }

}