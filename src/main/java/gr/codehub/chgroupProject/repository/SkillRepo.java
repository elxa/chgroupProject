package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {
}
