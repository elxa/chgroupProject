package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.JobOfferSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferSkillRepository extends JpaRepository<JobOfferSkill, Integer> {
}
