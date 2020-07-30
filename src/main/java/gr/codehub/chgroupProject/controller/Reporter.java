package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.dto.ApplicantSkillDTO;
import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.dto.skillsDontMatchToApplicantsDTO;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Reporter {

    @Autowired
    ApplicantSkillService applicantSkillService;
    @Autowired
    JobOfferSkillService jobOfferSkillService;

    @GetMapping("applicantSkills")
    public List<ApplicantSkillDTO> theMostOfferedSkillsinApplicants() {
        return applicantSkillService.theMostOfferedSkills();
    }

    @GetMapping("jobOfferSkills")
    public List<JobOfferSkillDTO> theMostOfferedSkillsInJobOffers() {
        return jobOfferSkillService.theMostOfferedSkillsInJobOffers();
    }

    @GetMapping("skillsWithNoApplicant")
    public List<skillsDontMatchToApplicantsDTO> skillsThatDontMathesToApplicants() {
        return applicantSkillService.skillsWhichDontMatchesToApplicants();
    }
}
