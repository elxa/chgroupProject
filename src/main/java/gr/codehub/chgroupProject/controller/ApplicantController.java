package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    /**
     * Getting all applicants
     * @return list of applicants
     */
    @GetMapping("applicant")
    public List<Applicant> getListApplicants() {
        return applicantService.getApplicants();
    }

    /**
     * Getting a certain applicant according with their id
     * @param applicantId specific applicant
     * @return give us back a specific applicant
     */
    @GetMapping("applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable int applicantId) throws ApplicantNotFoundException {
        return applicantService.getApplicantById(applicantId);
    }

    /**
     * Updating applicant according with their id
     * @param applicant
     * @param applicantId
     * @return updated applicant
     * @throws ApplicantNotFoundException
     */

    @PutMapping("applicant/{applicantId}")
    public Applicant updateApplicant(@RequestBody Applicant applicant, @PathVariable int applicantId) throws ApplicantNotFoundException {
        return applicantService.updateApplicant(applicant, applicantId);
    }

    /**
     * @param applicant
     * @return add a new applicant
     */
    @PostMapping("applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields {
        return applicantService.addApplicant(applicant);
    }

}
