package at.tourplannerapp.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TourLog {
    private Integer tourLogId;
    private String name;
    private String comment;
    private Integer difficulty;
    private Integer totalTime;
    private Integer rating;
    private LocalDate date;
    private LocalTime time;

    public TourLog(Integer tourLogId, String name, LocalDate date, LocalTime time, String comment, Integer difficulty, Integer totalTime, Integer rating) {
        this.tourLogId = tourLogId;
        this.name = name;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
        this.date = date;
        this.time = time;
    }
    @Override
    public String toString() {
        return name;
    }
}
