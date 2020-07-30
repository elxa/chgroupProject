package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.dto.ApplicantSkillDTO;
import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.dto.skillsDontMatchToApplicantsDTO;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class Reporter {
    Logger logger = LoggerFactory.getLogger(Reporter.class);

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

    @RequestMapping("/")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }



}
