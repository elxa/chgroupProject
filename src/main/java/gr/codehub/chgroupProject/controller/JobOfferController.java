package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.service.JobOfferService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class JobOfferController {

    Logger logger = LoggerFactory.getLogger(JobOfferController.class);

    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private JobOfferSkillService jobOfferSkillService;

    /**
     * Get a list of job offer. If user write in url path the companyName, region and nameOfSkill parameters will return this object
     *
     * @param companyName
     * @param region
     * @param nameOfSkill
     * @return
     * @throws JobOfferNotFoundException
     */
    @GetMapping("jobOffer")
    public List<JobOfferSkillDTO> getListOfJobOffers(@RequestParam(required = false) String companyName,
                                                     @RequestParam(required = false) String region,
                                                     @RequestParam(required = false) String nameOfSkill) throws JobOfferNotFoundException {
        logger.info("Return a list of job Offers");
        return jobOfferService.getJobOffers(companyName, region, nameOfSkill);
    }



    /**
     *Adding a new job offe3r
     * @param JobOffer
     * @return the new job offer
     * @throws JobOfferNotFoundException
     * @throws JobOfferNotValidFields
     */

    @PostMapping("jobOffer")
    public JobOffer addJobOffer(@RequestBody JobOffer JobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields {
        logger.info("Add a job Offer in db");
        return jobOfferService.addJobOffer(JobOffer);
    }

    /**
     * Getting a certain job offer according with their id
     * @param jobOfferId
     * @return
     * @throws JobOfferNotFoundException
     */

    @GetMapping("jobOffer/{jobOfferId}")
    public JobOffer getJobOfferById(@PathVariable int jobOfferId)
            throws JobOfferNotFoundException {
        logger.info("Get a job Offer by id");
        return jobOfferService.getJobOfferById(jobOfferId);//epistrefei ena json
    }

    /**
     * Updating the joboffer
     * @param jobOffer
     * @param jobOfferId
     * @return the updated jobOfferId and the jobOffer
     * @throws JobOfferNotFoundException
     */
    @PutMapping("jobOffer/{jobOfferId}")
    public JobOffer updateJobOffer(@RequestBody JobOffer jobOffer, @PathVariable int jobOfferId) throws JobOfferNotFoundException {
        logger.info("Update a job Offer");
        return jobOfferService.updateJobOffer(jobOffer, jobOfferId);
    }

    /**
     * Add a skill in job Offer
     *
     * @param jobOfferId
     * @param skillId
     * @return
     * @throws SkillNotFoundException
     * @throws JobOfferNotFoundException
     */
    //******************************************** JobOfferSkill Controller*********************************************
    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkill(@PathVariable int jobOfferId, @PathVariable int skillId) throws SkillNotFoundException, JobOfferNotFoundException {
        logger.info("Add a skill in job Offer");
        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }
}