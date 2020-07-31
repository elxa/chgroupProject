package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;

import java.util.List;
//todo update applicant
public interface ApplicantService {

    List<Applicant> getApplicants(String firstName, String lastName) throws ApplicantNotFoundException;

    Applicant addApplicant(Applicant applicant) throws ApplicantNotValidFields, ApplicantNotFoundException;

    Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException;

    Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException;

    List<Applicant> getListApplicants() throws ApplicantNotFoundException;
}
