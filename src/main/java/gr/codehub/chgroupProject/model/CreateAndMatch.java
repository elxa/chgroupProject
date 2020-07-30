package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreateAndMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dom;
    private boolean manualMatch;
    private Boolean finalized = false;

    @ManyToOne
    private Applicant applicant;

    @ManyToOne
    private JobOffer jobOffer;
}
