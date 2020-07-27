package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;

import java.util.List;
import java.util.Optional;
//todo update applicant
public interface ApplicantService {

    List<Applicant> getApplicants();
    Applicant addApplicant(Applicant applicant);

    // throws ApplicantNotFoundException;

    Applicant getApplicantById(int applicantId);

    Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException;

}
