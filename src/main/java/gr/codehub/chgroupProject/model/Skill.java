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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nameOfSkill;

    @JsonIgnore
    @OneToMany(mappedBy = "skill")
    @ToString.Exclude
    private List<ApplicantSkill> applicantSkills;
    @JsonIgnore
    @OneToMany(mappedBy = "skill")
    @ToString.Exclude
    private List<JobOfferSkill> jobOfferSkills;
}
