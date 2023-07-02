package at.tourplannerapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponseModel {
    private Double distance;
    private Long time;
    private String session;
    private Integer statusCode;

    public RouteResponseModel(Double distance, Long time, String session, Integer statusCode) {
        this.distance = distance;
        this.time = time;
        this.session = session;
        this.statusCode = statusCode;
    }
}
