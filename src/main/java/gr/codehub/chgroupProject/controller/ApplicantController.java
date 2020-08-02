package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.dto.ApplicantSkillDto;
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
     * Getting all applicants
     * @return list of applicants
     */
//    @GetMapping("applicant")
//    public List<ApplicantSkillDto> getListApplicants(@RequestParam(required = false) String firstName,
//                                                     @RequestParam(required = false) String lastName,
//                                                     @RequestParam(required = false) String skillName) throws ApplicantNotFoundException {
//        logger.info("Take a list of applicants");
//       // return applicantService.getApplicants(firstName, lastName);
//        return applicantService.getApplicants(firstName, lastName, skillName);
//    }

    @GetMapping("applicant")
    public List<Applicant> getListApplicants() throws ApplicantNotFoundException {
        return applicantService.getListApplicants();
    }

    /**
     * Getting a certain applicant according with their id
     * @param applicantId specific applicant
     * @return give us back a specific applicant
     */
    @GetMapping("applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable int applicantId) throws ApplicantNotFoundException {
        logger.info("Take an  Applicant By Id");
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

    /**
     * Add a skill in an applicant
     *
     * @param applicantId
     * @param skillId
     * @return
     * @throws ApplicantNotFoundException
     * @throws SkillNotFoundException
     */
    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId) throws ApplicantNotFoundException, SkillNotFoundException {
        logger.info("Add a skill in an Applicant");
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }


}
