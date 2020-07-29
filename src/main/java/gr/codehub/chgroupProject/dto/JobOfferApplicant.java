package gr.codehub.chgroupProject.dto;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class JobOfferApplicant {
    JobOffer jobOffer;
    Applicant applicant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobOfferApplicant)) return false;
        JobOfferApplicant that = (JobOfferApplicant) o;
        return jobOffer.equals(that.jobOffer) &&
                applicant.equals(that.applicant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobOffer, applicant);
    }
}
