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
//    @Query("SELECT cam FROM CreateAndMatch cam WHERE cam.applicant_id = ?1 AND cam.jobOffer_id = ?2")
//    JobOffer checkIfApplicantIdAndJobOfferIdExist(int applicantId, int jobOfferId);
    Optional<CreateAndMatch> findCreateAndMatchByJobOfferAndApplicant(JobOffer jobOffer, Applicant applicant); //query lookup strategy
}
