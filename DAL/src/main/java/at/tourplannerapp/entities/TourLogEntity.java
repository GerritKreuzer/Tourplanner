package at.tourplannerapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor // mandatory for every hibernate entity
@Entity
public class TourLogEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer tourLogId;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "duration")
    private Integer totalTime;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_item_id", nullable = false, updatable = false)
    private TourItemEntity tourItemEntity;

    public TourLogEntity(TourItemEntity tourItemEntity) {
        this.tourItemEntity = tourItemEntity;
    }
}
