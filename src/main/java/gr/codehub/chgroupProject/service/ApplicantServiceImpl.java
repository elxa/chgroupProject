package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ApplicantServiceImpl implements ApplicantService{
    @Autowired
    ApplicantRepository applicantRepo;


    @Override
    public Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException {

        Applicant applicantInDb = applicantRepo.findApplicantByFirstNameAndLastName(firstName, lastName)
                .orElseThrow( () -> new ApplicantNotFoundException("Applicant Not found"));

        return applicantInDb;
    }
}
