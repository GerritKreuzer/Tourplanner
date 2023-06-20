package at.tourplannerapp.service;

public interface MapService {
    Double getDistance(String transportType, String fromLocation, String toLocation);

    Long getTime(String transportType, String fromLocation, String toLocation);

    byte[] fetchImageAsByteArray(String fromLocation, String toLocation);

}
