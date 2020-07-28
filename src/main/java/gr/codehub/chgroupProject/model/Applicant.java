package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
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
    /**
     * Set id as a PrimaryKey
     * @Id
     * Create auto values on primary key
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Set the colums and names on the database
     */
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String region;
    @NotNull
    private String education;
    @NotNull
    private String level;
    private Boolean available = true;


    /**
     * Set the Foreign key
     */
    @JsonIgnore
    @OneToMany(mappedBy = "applicant")
    private List<ApplicantSkill> applicantSkills;

    @OneToMany(mappedBy = "applicant")
    private List<CreateAndMatch> createAndMatches;
}
