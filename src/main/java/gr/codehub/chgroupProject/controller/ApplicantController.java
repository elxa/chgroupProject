package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private ApplicantSkillService applicantSkillService;

    /**
     * @return list off aplicants
     */
    @GetMapping("applicant")
    public List<Applicant> getListApplicants() {
        return applicantService.getApplicants();
    }

    /**
     * @param applicantId specific applicant
     * @return give us back a specific applicant
     */
    @GetMapping("applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable int applicantId) throws ApplicantNotFoundException {
        return applicantService.getApplicantById(applicantId);
    }

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

    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId) throws ApplicantNotFoundException, SkillNotFoundException {
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }


}
