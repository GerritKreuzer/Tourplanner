package at.tourplannerapp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH/mm")
    private LocalTime totalTime;
    private Integer rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH/mm")
    private LocalTime time;

    public TourLog() {
    }

    public TourLog(Integer tourLogId, String name, LocalDate date, LocalTime time, String comment, Integer difficulty, LocalTime totalTime, Integer rating) {
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
