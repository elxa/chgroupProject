package gr.codehub.chgroupProject.controller;

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

    @GetMapping("jobOffer")
    public List<JobOffer> getListOfJobOffers(@RequestParam(required = false) String companyName,
                                             @RequestParam(required = false) String region,
                                             @RequestParam(required = false) String nameOfSkill) throws JobOfferNotFoundException {
        logger.info("Return a list of job Offers");
        return jobOfferService.getJobOffers(companyName, region, nameOfSkill);
    }


    @PostMapping("jobOffer")
    public JobOffer addJobOffer(@RequestBody JobOffer JobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields {
        logger.info("Add a job Offer in db");
        return jobOfferService.addJobOffer(JobOffer);
    }

    @GetMapping("jobOffer/{jobOfferId}")
    public JobOffer getJobOfferById(@PathVariable int jobOfferId)
            throws JobOfferNotFoundException {
        logger.info("Get a job Offer by id");
        return jobOfferService.getJobOfferById(jobOfferId);//epistrefei ena json
    }

    @PutMapping("jobOffer/{jobOfferId}")
    public JobOffer updateJobOffer(@RequestBody JobOffer jobOffer, @PathVariable int jobOfferId) throws JobOfferNotFoundException {
        logger.info("Update a job Offer");
        return jobOfferService.updateJobOffer(jobOffer,jobOfferId);
    }

    //******************************************** JobOfferSkill Controller*********************************************
    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkillService(@PathVariable int jobOfferId, @PathVariable int skillId) throws SkillNotFoundException, JobOfferNotFoundException {
        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }
}
