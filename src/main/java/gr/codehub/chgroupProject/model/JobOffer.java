package gr.codehub.chgroupProject.model;

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

    private String name;
    private String level;
    private String position;
    private String region;
    private boolean available= false;

    @OneToMany(mappedBy = "jobOffer")
    private List<JobOfferSkill> jobOfferSkill;

    @OneToMany(mappedBy = "jobOffer")
    private List<CreateAndMatch> createAndMatche;
}
