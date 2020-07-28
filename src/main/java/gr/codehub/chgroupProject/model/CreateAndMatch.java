package gr.codehub.chgroupProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreateAndMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateOfMatch; //todo hmeromhnia pou egine to match
    private LocalDate dateOfHired; //todo hmeromhnia pou egine to Hired
    private boolean manualMatch;
    private boolean available = false;

    @ManyToOne
    private Applicant applicant;

    @ManyToOne
    private JobOffer jobOffer;
}
