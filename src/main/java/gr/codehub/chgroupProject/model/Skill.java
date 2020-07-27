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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nameOfSkill;

    @OneToMany(mappedBy = "skill")
    private List<ApplicantSkill> applicantSkills;

    @OneToMany(mappedBy = "skill")
    private List<JobOfferSkill> jobOfferSkills;
}
