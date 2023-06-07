package at.tourplannerapp.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // mandatory for every hibernate entity
@Entity
public class Tour {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer tourId;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "transportation_type", nullable = false)
    private String transportationType;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "estimated_time", nullable = false)
    private Integer estimatedTime;

    @Column(name = "path_to_map", nullable = false)
    private String pathToMap;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "from_location", nullable = false)
    private String fromLocation;

    @Column(name = "to_location", nullable = false)
    private String toLocation;

    public Tour(String name, String description, String transportationType, Double distance, Integer estimatedTime, String pathToMap, String username, String fromLocation, String toLocation) {
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
