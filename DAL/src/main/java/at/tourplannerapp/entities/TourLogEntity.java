package at.tourplannerapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.springframework.stereotype.Indexed;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor // mandatory for every hibernate entity
@Entity
@Indexed
public class TourLogEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer tourLogId;

    @Column(name = "name")
    @Field
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "comment")
    private String comment;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "duration")
    private LocalTime totalTime;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_item_id", nullable = false, updatable = false)
    private TourItemEntity tourItemEntity;

    public TourLogEntity(TourItemEntity tourItemEntity) {
        this.tourItemEntity = tourItemEntity;
    }
}
