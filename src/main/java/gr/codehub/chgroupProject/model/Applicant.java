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


    @JsonIgnore
    @OneToMany(mappedBy = "applicant")
    private List<ApplicantSkill> applicantSkills;
//
//    @OneToMany(mappedBy = "applicant")
//    private List<CreateAndMatch> createAndMatches;
}
