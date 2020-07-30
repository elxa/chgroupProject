package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @JoinColumn(name = "applicant", referencedColumnName = "applicant")
    @ManyToOne
    private Applicant applicant;
//    @JoinColumn(name = "jobOffer", referencedColumnName = "jobOffer")
    @ManyToOne
    private JobOffer jobOffer;
}
