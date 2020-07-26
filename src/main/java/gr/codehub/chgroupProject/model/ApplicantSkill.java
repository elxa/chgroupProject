package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ApplicantSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Skill skill;

    /**
     * Specify a relationship from the embeddable class to an entity class.
     * @ManyToOne
     */
    @JsonIgnore
    @ManyToOne
    private Applicant applicant;
}
