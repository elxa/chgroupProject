package gr.codehub.chgroupProject.repository;


import gr.codehub.chgroupProject.dto.ApplicantSkillDTO;
import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {
    @Query(value = "SELECT TOP(20) sk.nameOfSkill as SkillName, COUNT(*) as howManyTimesAppeared FROM JobOfferSkill js, Skill sk where js.skill_id = sk.id group by sk.nameOfSkill ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<JobOfferSkillDTO> howManyTimesSkillAppearsInJobOfferSkills();
}
