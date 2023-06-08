package at.tourplannerapp.model;

import jakarta.persistence.Column;

public class TourItem {

    private Integer tourId;
    private String name;
    private String description;
    private String transportationType;
    private Double distance;
    private Integer estimatedTime;
    private String pathToMap;
    private String username;
    private String fromLocation;
    private String toLocation;

    public TourItem() {
    }

    public TourItem(Integer tourId, String name, String description, String transportationType, Double distance, Integer estimatedTime, String pathToMap, String username, String fromLocation, String toLocation) {
        this.tourId = tourId;
        this.name = name;
        this.description = description;
        this.transportationType = transportationType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.pathToMap = pathToMap;
        this.username = username;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getPathToMap() {
        return pathToMap;
    }

    public void setPathToMap(String pathToMap) {
        this.pathToMap = pathToMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    @Override
    public String toString() {
        return name;
    }
}
