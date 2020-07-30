package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateAndMatchRepository extends JpaRepository<CreateAndMatch, Integer> {

    Optional<CreateAndMatch> findCreateAndMatchByJobOfferAndApplicant(JobOffer jobOffer, Applicant applicant); //query lookup strategy
}
