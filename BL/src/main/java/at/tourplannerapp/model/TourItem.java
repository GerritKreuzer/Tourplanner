package at.tourplannerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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
    private Long estimatedTime;
    private byte[] map;
    private String fromLocation;
    private String toLocation;

    private Integer popularity;

    private Integer childFriendliness;

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

    @JsonIgnore
    public void setCalculatedProperties(List<TourLog> tourLogList) {
        this.setPopularity(tourLogList.size());
        this.setChildFriendliness(calculateChildFriendliness(tourLogList));
    }
    @JsonIgnore
    private Integer calculateChildFriendliness(List<TourLog> tourLogs) {
        double childFriendliness = 0.0;
        childFriendliness += getDistanceFriendliness(distance);
        childFriendliness += getTotalTimesFriendliness(tourLogs);
        childFriendliness += getDifficultyFriendliness(tourLogs);
        return (int) Math.round(childFriendliness / 3.0);
    }
    @JsonIgnore
    private Integer getDistanceFriendliness(Double distance) {
        if (distance == null || distance == 0) {
            return 0;
        }

        int intDistance = (int) Math.round(distance);
        if (intDistance > 100) {
            return 0;
        } else {
            return (10 - (int) Math.round(intDistance / 10.0));
        }
    }
    @JsonIgnore
    private Integer getTotalTimesFriendliness(List<TourLog> tourLogs) {
        long secSum = 0;
        if (tourLogs.isEmpty()) {
            return 0;
        }

        for (TourLog log : tourLogs) {
            if (log.getTotalTime() == null) {
                continue;
            }
            secSum += log.getTotalTime().toSecondOfDay();
        }
        if (secSum == 0) return 0;
        return (10 - (int) Math.round(secSum / (3600 * 2.4)));
    }
    @JsonIgnore
    private Integer getDifficultyFriendliness(List<TourLog> tourLogs) {
        long difficulty = 0;
        if (tourLogs.isEmpty()) {
            return 0;
        }

        for (TourLog log : tourLogs) {
            if (log.getDifficulty() == null) {
                continue;
            }
            difficulty += log.getDifficulty();
        }
        if (difficulty == 0) return 0;
        return (10 - Math.toIntExact(difficulty / tourLogs.size()));
    }
}
