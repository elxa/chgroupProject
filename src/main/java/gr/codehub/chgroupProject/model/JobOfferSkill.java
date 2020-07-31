package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobOfferSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //    @JoinColumn(name = "skill", referencedColumnName = "skill")
    @ManyToOne
    private Skill skill;
    //    @JoinColumn(name = "jobOffer", referencedColumnName = "jobOffer")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private JobOffer jobOffer;
}
