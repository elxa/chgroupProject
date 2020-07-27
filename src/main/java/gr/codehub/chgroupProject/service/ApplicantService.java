package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;

import java.util.Optional;

public interface ApplicantService{
    Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException;
}
