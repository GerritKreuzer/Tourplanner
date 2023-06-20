package at.tourplannerapp.service;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public interface MapService {
    Double getDistance(String fromLocation, String toLocation);

    Long getTime(String fromLocation, String toLocation);

    Image fetchImage();

}
