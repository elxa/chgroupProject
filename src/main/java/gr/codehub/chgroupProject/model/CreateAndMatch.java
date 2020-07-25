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

    private LocalDate dom;
    private boolean manualMatch;
    private boolean available = false;

    @ManyToOne
    private Applicant applicant;

    @ManyToOne
    private JobOffer jobOffer;
}
