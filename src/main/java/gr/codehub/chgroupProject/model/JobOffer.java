package gr.codehub.chgroupProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    private boolean available;

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer")
    private List<JobOfferSkill> jobOfferSkill;

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer")
    private List<CreateAndMatch> createAndMatche;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobOffer)) return false;
        JobOffer jobOffer = (JobOffer) o;
        return id == jobOffer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
