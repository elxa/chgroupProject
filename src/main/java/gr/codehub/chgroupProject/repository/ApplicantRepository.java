package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

    Optional<Applicant> findApplicantByFirstNameAndLastName(String firstName, String lastName);//briskei ton customer analoga me to onoma tou
}
