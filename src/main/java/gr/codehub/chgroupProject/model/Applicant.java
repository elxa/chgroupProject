package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Create an Applicant table in database
 * @Entity
 * Create auto Getter,Setter,ToString and Constructor
 * @Data
 * Generates a constructor with 1 parameter for each field in the class
 * @AllArgsConstructor
 * Will generate a constructor with no parameters
 * @NoArgsConstructor
 */
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


        /**
         * Set the Foreign key
         */
        @JsonIgnore
        @OneToMany(mappedBy = "applicant")
        private List<ApplicantSkill> applicantSkills;

    @OneToMany(mappedBy = "applicant")
    private List<CreateAndMatch> createAndMatches;
}
