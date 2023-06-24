package at.tourplannerapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponseModel {
    private Double distance;
    private Long time;
    public RouteResponseModel(Double distance, Long time) {
        this.distance = distance;
        this.time = time;
    }
}
