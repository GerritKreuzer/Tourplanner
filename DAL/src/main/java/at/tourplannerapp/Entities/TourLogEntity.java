package at.tourplannerapp.Entities;

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

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "duration", nullable = false)
    private Integer totalTime;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_item_id", nullable = false, updatable = false)
    private TourItemEntity tourItemEntity;

    public TourLogEntity(Integer tourLogId, Date date, String comment, Integer difficulty, Integer totalTime, Integer rating) {
        this.tourLogId = tourLogId;
        this.date = date;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }
}
