package at.tourplannerapp.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TourLog {

    private Integer tourLogId;

    private Date date;

    private String comment;

    private Integer difficulty;

    private Integer totalTime;

    private Integer rating;

    public TourLog(Integer tourLogId, Date date, String comment, Integer difficulty, Integer totalTime, Integer rating) {
        this.tourLogId = tourLogId;
        this.date = date;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }
}
