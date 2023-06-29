package at.tourplannerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class TourItem {
    @JsonIgnore
    private Integer tourId;
    private String name;
    private String description;
    private String transportationType;
    private Double distance;
    private Long estimatedTime;
    private byte[] map;
    private String fromLocation;
    private String toLocation;

    public TourItem() {
    }

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

    @JsonIgnore
    public String getFormattedStringForEstimatedTime() {
        int day = (int) TimeUnit.SECONDS.toDays(estimatedTime);
        long hours = TimeUnit.SECONDS.toHours(estimatedTime) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(estimatedTime) - (TimeUnit.SECONDS.toHours(estimatedTime) * 60);
        long second = TimeUnit.SECONDS.toSeconds(estimatedTime) - (TimeUnit.SECONDS.toMinutes(estimatedTime) * 60);

        StringBuilder str = new StringBuilder();

        if (day != 0) {
            if (day == 1) {
                str.append("1 Day ");
            } else {
                str.append(day);
                str.append(" Days ");
            }
        }
        str.append(String.format("%02d", hours));
        str.append(":");
        str.append(String.format("%02d", minute));
        str.append(":");
        str.append(String.format("%02d", second));
        return str.toString();
    }
}
