package at.tourplannerapp.service;

import at.tourplannerapp.dto.RouteMatrixRequestBody;

import java.awt.image.BufferedImage;

public interface MapService {
    Double getDistance(RouteMatrixRequestBody routeMatrixRequestBody);

    Long getTime(RouteMatrixRequestBody routeMatrixRequestBody);

    BufferedImage fetchAndSaveImage();

}
