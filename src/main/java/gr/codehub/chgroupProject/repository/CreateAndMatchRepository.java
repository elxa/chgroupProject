package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.dto.ApplicantSkillDTO;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreateAndMatchRepository extends JpaRepository<CreateAndMatch, Integer> {

    Optional<CreateAndMatch> findCreateAndMatchByJobOfferAndApplicant(JobOffer jobOffer, Applicant applicant); //query lookup strategy

    @Query(value = "SELECT *" +
            "FROM CreateAndMatch cam " +
            "WHERE cam.manualMatch = 1", nativeQuery = true)
    List<CreateAndMatch> listOfManualCreateAndMatch();

    @Query(value = "SELECT *" +
            "FROM CreateAndMatch cam " +
            "WHERE cam.manualMatch = 0", nativeQuery = true)
    List<CreateAndMatch> listOfAutomaticCreateAndMatch();
}
