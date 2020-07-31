package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;

import java.util.List;

public interface ApplicantService {

    List<Applicant> getApplicants();

    Applicant addApplicant(Applicant applicant)
            throws ApplicantNotValidFields, ApplicantNotFoundException;

    Applicant updateApplicant(Applicant applicant, int applicantId)
            throws ApplicantNotFoundException;

    Applicant getApplicantById(int applicantId)
            throws ApplicantNotFoundException;

//    Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException;

}
