package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String position;
    private String region;
    private boolean available;

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer")
    private List<JobOfferSkill> jobOfferSkill;

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer")
    private List<CreateAndMatch> createAndMatche;
}
