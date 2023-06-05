package at.tourplannerapp.dataClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    private Double estimatedTime;

    @Column(name = "path_to_map", nullable = false)
    private String pathToMap;

    @Column(name = "user", nullable = false)
    private String user;

    @Column(name = "from", nullable = false)
    private String from;

    @Column(name = "to", nullable = false)
    private String to;
}
