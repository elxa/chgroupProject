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
    /**
     * Set id as a PrimaryKey
     *
     * @Id Create auto values on primary key
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        /**
         * Set the colums and names on the database
         */
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
