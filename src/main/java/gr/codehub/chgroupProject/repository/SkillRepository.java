package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query("SELECT s FROM Skill s WHERE s.nameOfSkill = ?1")
    Optional<Skill> findSkillByName(String skillName);
}
