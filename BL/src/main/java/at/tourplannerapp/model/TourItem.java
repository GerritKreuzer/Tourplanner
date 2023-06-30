package at.tourplannerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
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
    private String estimatedTimeString;
    @Setter(AccessLevel.NONE)
    private Long estimatedTime;
    private byte[] map;
    private String fromLocation;
    private String toLocation;

    public void setEstimatedTime(Long estimatedTime) {
        this.estimatedTime = estimatedTime;
        this.estimatedTimeString = getFormattedStringForEstimatedTime(estimatedTime);
    }

    public TourItem() {

    }

    public TourItem(Integer tourId, String name, String description, String transportationType, Double distance, Long estimatedTime, byte[] map, String fromLocation, String toLocation) {
        this.tourId = tourId;
        this.name = name;
        this.description = description;
        this.transportationType = transportationType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.estimatedTimeString = getFormattedStringForEstimatedTime(estimatedTime);
        this.map = map;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonIgnore
    public String getFormattedStringForEstimatedTime(Long estimatedTimeLong) {
        if(estimatedTimeLong == null) {
            return null;
        }
        int day = (int) TimeUnit.SECONDS.toDays(estimatedTimeLong);
        long hours = TimeUnit.SECONDS.toHours(estimatedTimeLong) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(estimatedTimeLong) - (TimeUnit.SECONDS.toHours(estimatedTimeLong) * 60);
        long second = TimeUnit.SECONDS.toSeconds(estimatedTimeLong) - (TimeUnit.SECONDS.toMinutes(estimatedTimeLong) * 60);

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
