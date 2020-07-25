package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository <Applicant, Integer> {
}
