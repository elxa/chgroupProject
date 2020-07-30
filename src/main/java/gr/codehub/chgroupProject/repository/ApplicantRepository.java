package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.dto.ApplicantSkillDTO;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Optional<Applicant> findApplicantByFirstNameAndLastName(String firstName, String lastName);//briskei ton customer analoga me to onoma tou

    @Query(value = "SELECT TOP(20) s.nameOfSkill as SkillName, COUNT(*) as howManyTimesAppeared FROM ApplicantSkill askill, Skill s WHERE askill.skill_id = s.id GROUP BY s.nameOfSkill ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<ApplicantSkillDTO> howManyTimesSkillAppearsInApplicantSkills();



}
