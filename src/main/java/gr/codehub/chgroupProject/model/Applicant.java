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
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String address;
    private String region;
    private String education;
    private boolean available = false;


    @OneToMany(mappedBy = "applicant")
    private List<ApplicantSkill> applicantSkills;
//
//    @OneToMany(mappedBy = "applicant")
//    private List<CreateAndMatch> createAndMatches;
}
