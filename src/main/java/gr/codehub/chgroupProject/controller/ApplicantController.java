package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApplicantController {
    Logger logger = LoggerFactory.getLogger(ApplicantController.class);

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private ApplicantSkillService applicantSkillService;

    /**
     * @return list off aplicants
     */
    @GetMapping("applicant")
    public List<Applicant> getListApplicants(@RequestParam(required = false) String firstName,
                                             @RequestParam(required = false) String lastName) throws ApplicantNotFoundException {
        logger.info("Take a list of applicants");
        return applicantService.getApplicants(firstName, lastName);
    }

    /**
     * @param applicantId specific applicant
     * @return give us back a specific applicant
     */
    @GetMapping("applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable int applicantId) throws ApplicantNotFoundException {
        logger.info("Take an  Applicant By Id");
        return applicantService.getApplicantById(applicantId);
    }

    @PutMapping("applicant/{applicantId}")
    public Applicant updateApplicant(@RequestBody Applicant applicant, @PathVariable int applicantId) throws ApplicantNotFoundException {
        logger.info("Take an  updated Applicant");
        return applicantService.updateApplicant(applicant, applicantId);
    }

    /**
     * @param applicant
     * @return add a new applicant
     */
    @PostMapping("applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields {
        logger.info("Add an Applicant in db");
        return applicantService.addApplicant(applicant);
    }

    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId) throws ApplicantNotFoundException, SkillNotFoundException {
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }


}
