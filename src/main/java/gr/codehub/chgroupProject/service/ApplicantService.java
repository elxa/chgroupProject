package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.model.Applicant;

import java.util.List;

public interface ApplicantService {

    List<Applicant> getApplicant();
    Applicant addApplicant(Applicant applicant);
    //Applicant updateApplicant(Applicant applicant, int applicantId);
    // throws ApplicantNotFoundException;
    Applicant getApplicant(int applicantId);

}
