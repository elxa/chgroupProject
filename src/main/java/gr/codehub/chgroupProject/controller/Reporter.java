package gr.codehub.chgroupProject.controller;
//applicant?name=spyros&region=athens

import gr.codehub.chgroupProject.dto.ApplicantOfferedSkillDTO;
import gr.codehub.chgroupProject.dto.JobOfferRequiredSkillDTO;
import gr.codehub.chgroupProject.dto.skillsDontMatchToApplicantsDTO;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.RetrieveDatesException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import gr.codehub.chgroupProject.service.matcher.AutomaticMatchService;
import gr.codehub.chgroupProject.service.matcher.FinalizeService;
import gr.codehub.chgroupProject.service.matcher.ManualMatchService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Reporter {
    Logger logger = LoggerFactory.getLogger(Reporter.class);

    @Autowired
    ApplicantSkillService applicantSkillService;
    @Autowired
    JobOfferSkillService jobOfferSkillService;
    @Autowired
    AutomaticMatchService automaticMatchService;
    @Autowired
    ManualMatchService manualMatchService;
    @Autowired
    FinalizeService finalizeService;

    //********************************* 1o reporter ******************************************************
    //Retrieve the list of the 20 most requested and offered skills.

    /**
     * Retrieve the list of the 20 most offered skills
     *
     * @return Retrieve the list of the 20 most offered skills.
     */

    @GetMapping("mostOfferedApplicantSkills")
    public List<ApplicantOfferedSkillDTO> theMostOfferedSkillsinApplicants() {
        logger.info("Retrieve the list of the 20 most offered skills.");
        return applicantSkillService.theMostOfferedSkills();
    }

    /**
     * Retrieve the list of the 20 most requested skills.
     *
     * @return Retrieve the list of the 20 most requested skills.
     */

    @GetMapping("mostOfferedJobOfferedSkills")
    public List<JobOfferRequiredSkillDTO> theMostOfferedSkillsInJobOffers() {
        logger.info("Retrieve the list of the 20 most requested skills.");
        return jobOfferSkillService.theMostOfferedSkillsInJobOffers();
    }

    //********************************* 2o reporter ******************************************************
    //Retrieve the list of the not matched skills by the applicants

    /**
     * Retrieve the list of the not matched skills by the applicants
     *
     * @return Retrieve the list of the not matched skills by the applicants
     */

    @GetMapping("skillsNotMatchedWithApplicants")
    public List<skillsDontMatchToApplicantsDTO> skillsThatDontMathesToApplicants() {
        logger.info("Retrieve the list of the not matched skills by the applicants");
        return applicantSkillService.skillsWhichDontMatchesToApplicants();
    }

    //********************************* 4o reporter ******************************************************
    //Retrieve the list of the 20 recent finalized matches applicants-offers

    /**
     * Retrieve the list of the 20 recent finalized matches applicants-offers
     *
     * @return Retrieve the list of the 20 recent finalized matches applicants-offers
     */
    @GetMapping("finalizedMatches")
    public List<CreateAndMatch> listOfFinalizedCreateAndMatch() {
        logger.info("Retrieve the list of the 20 recent finalized matches applicants-offers");
        return finalizeService.finalizedList();
    }

    //********************************* 5o reporter ******************************************************
    //Provide weekly & monthly reports for weekly and monthly finalized

    /**
     * Provide weekly & monthly reports for weekly and monthly finalized
     * @param startDate the date which you want to start take the list of reports
     * @param endDate the date which you want to take the list of reports
     * @return Provide weekly & monthly reports for weekly and monthly finalized
     */
    @GetMapping("dateReporter")
    public List<CreateAndMatch> weeklyAndMonthlyRecords(@RequestParam(required = false) String startDate,
                                                        @RequestParam(required = false) String endDate) throws CreateAndMatchNotFound, RetrieveDatesException {
        logger.info("Provide weekly & monthly reports for weekly and monthly finalized");
        return finalizeService.weeklyAndMonthlyRecords(startDate, endDate);
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