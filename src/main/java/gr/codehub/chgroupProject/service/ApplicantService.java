package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;

import java.util.List;
//todo update applicant
public interface ApplicantService {

    List<Applicant> getApplicants();

    Applicant addApplicant(Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields;

    Applicant updateApplicant(Applicant applicant, int applicantId)
            throws ApplicantNotFoundException;

    Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException;

    Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException;

}
