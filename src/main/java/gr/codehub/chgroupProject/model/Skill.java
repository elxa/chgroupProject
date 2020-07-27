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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameOfSkill;
    private String levelOfSkill;

    @JsonIgnore
    @OneToMany(mappedBy = "skill")
    private List<ApplicantSkill> applicantSkills;
    @JsonIgnore
    @OneToMany(mappedBy = "skill")
    private List<JobOfferSkill> jobOfferSkills;
}
