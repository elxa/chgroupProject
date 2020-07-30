package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.service.JobOfferService;
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

    @GetMapping("jobOffer")
    public List<JobOffer> getListOfJobOffers() {
        logger.info("Return a list of job Offers");
        return jobOfferService.getJobOffers();
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
}
