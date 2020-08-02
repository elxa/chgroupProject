package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreateAndMatchRepository extends JpaRepository<CreateAndMatch, Integer> {

    Optional<CreateAndMatch> findCreateAndMatchByJobOfferAndApplicant(JobOffer jobOffer, Applicant applicant); //query lookup strategy

    @Query(value = "SELECT *" +
            "FROM CreateAndMatch cam " +
            "WHERE cam.manualMatch = 1", nativeQuery = true)
    List<CreateAndMatch> listOfManualCreateAndMatch();

//    @Query(value = "SELECT *" +
//            "FROM CreateAndMatch cam " +
//            "WHERE cam.manualMatch = 0", nativeQuery = true)
//    List<CreateAndMatch> listOfAutomaticCreateAndMatch();

    @Query(value = "SELECT TOP 20 *  " +
            "FROM CreateAndMatch cam " +
            "WHERE cam.finalized = 1 " +
            "ORDER BY cam.dom", nativeQuery = true)
    List<CreateAndMatch> finalizedList();

    @Query(value = "SELECT * " +
            "FROM CreateAndMatch cam " +
            "WHERE cam.dom >= ?1 " +
            "AND cam.dom   <= ?2 " +
            "AND cam.finalized=1;", nativeQuery = true)
    List<CreateAndMatch> weeklyAndMonthlyRecords(LocalDate startDate, LocalDate endDate);
}