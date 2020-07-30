package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.dto.skillsDontMatchToApplicantsDTO;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, Integer>{

    @Query(value = "SELECT j.companyName, s.nameOfSkill, ask.applicant_id " +
            "FROM JobOffer j " +
            "INNER JOIN JobOfferSkill jos " +
            "ON J.id = jos.jobOffer_id " +
            "INNER JOIN Skill s " +
            "ON jos.skill_id = s.id " +
            "LEFT JOIN ApplicantSkill ask " +
            "ON s.id = ask.skill_id " +
            "WHERE ask.applicant_id is Null", nativeQuery = true)
    List<skillsDontMatchToApplicantsDTO> skillsWhichDontMatchesToApplicants();
}
