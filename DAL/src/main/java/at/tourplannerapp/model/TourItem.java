package at.tourplannerapp.model;

public class TourItem {
    private Integer id;
    private String name;
    private String description;
    private Double fromLocation;
    private Double toLocation;
    private String transportType;
    private Double duration;


    public TourItem(Integer id, String name, String description, Double fromLocation, Double toLocation, String transportType, Double duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.transportType = transportType;
        this.duration = duration;
    }

    public TourItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Double fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Double getToLocation() {
        return toLocation;
    }

    public void setToLocation(Double toLocation) {
        this.toLocation = toLocation;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    @Override
    public String toString() {
        return name;
    }
}
