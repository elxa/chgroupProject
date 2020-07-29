package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @NotNull
    private String companyName;
    @NotNull
    private String position;
    @NotNull
    private String region;
    @NotNull
    private String level;
    private Boolean available = true;


    @OneToMany(mappedBy = "jobOffer")
    private List<JobOfferSkill> jobOfferSkill;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "jobOffer")
    private List<CreateAndMatch> createAndMatche;
}
