package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {
    Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException;

    List<Applicant> getApplicant();

    Applicant addApplicant(Applicant applicant);

    //Applicant updateApplicant(Applicant applicant, int applicantId);
    // throws ApplicantNotFoundException;
    boolean deleteApplicant(int applicantId);

    Applicant getApplicant(int applicantId);
}
