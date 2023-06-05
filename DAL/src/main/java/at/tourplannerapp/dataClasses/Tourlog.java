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
public class Tourlog {

    @Id
    @GeneratedValue
    private Integer tourLogId;


}
