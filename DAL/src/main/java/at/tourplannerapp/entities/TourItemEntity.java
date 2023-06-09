package at.tourplannerapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // mandatory for every hibernate entity
@Entity
public class TourItemEntity {

    @OneToMany(mappedBy = "tourItemEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private final List<TourLogEntity> tourLogEntities = new ArrayList<>();
    @TableGenerator(
            name = "TourItemEntityGenerator",
            allocationSize = 1,
            initialValue = 0)
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "TourItemEntityGenerator")
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
    private Long estimatedTime;
    @Column(name = "map")
    private byte[] map;
    @Column(name = "from_location")
    private String fromLocation;
    @Column(name = "to_location")
    private String toLocation;

    public TourItemEntity(String name, String description, String transportationType, Double distance, Long estimatedTime, byte[] map, String fromLocation, String toLocation) {
        this.name = name;
        this.description = description;
        this.transportationType = transportationType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.map = map;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public TourItemEntity(Integer tourId, String name, String description, String transportationType, Double distance, Long estimatedTime, byte[] map, String fromLocation, String toLocation) {
        this.tourId = tourId;
        this.name = name;
        this.description = description;
        this.transportationType = transportationType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.map = map;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }
}
