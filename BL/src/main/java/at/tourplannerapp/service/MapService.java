package at.tourplannerapp.service;

import at.tourplannerapp.model.RouteResponseModel;

public interface MapService {
    RouteResponseModel getRoute(String transportType, String fromLocation, String toLocation);
    byte[] fetchImageAsByteArray(String fromLocation, String toLocation);
}
