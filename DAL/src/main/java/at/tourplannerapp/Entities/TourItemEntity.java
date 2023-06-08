package at.tourplannerapp.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // mandatory for every hibernate entity
@Entity
public class TourItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer tourId;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "transportation_type")
    private String transportationType;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "estimated_time")
    private Integer estimatedTime;

    @Column(name = "path_to_map")
    private String pathToMap;

    @Column(name = "username")
    private String username;

    @Column(name = "from_location")
    private String fromLocation;

    @Column(name = "to_location")
    private String toLocation;

    public TourItemEntity(String name, String description, String transportationType, Double distance, Integer estimatedTime, String pathToMap, String username, String fromLocation, String toLocation) {
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

    public TourItemEntity(Integer tourId, String name, String description, String transportationType, Double distance, Integer estimatedTime, String pathToMap, String username, String fromLocation, String toLocation) {
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
}
