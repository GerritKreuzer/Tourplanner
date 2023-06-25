package at.tourplannerapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TourItem {
    private Integer tourId;
    private String name;
    private String description;
    private String transportationType;
    private Double distance;
    private Long estimatedTime;
    private byte[] map;
    private String fromLocation;
    private String toLocation;

    public TourItem(){}

    public TourItem(Integer tourId, String name, String description, String transportationType, Double distance, Long estimatedTime, byte[] map, String fromLocation, String toLocation) {
        this.tourId = tourId;
        this.name = name;
        this.description = description;
        this.transportationType = transportationType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.map = map;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    @Override
    public String toString() {
        return name;
    }
}
