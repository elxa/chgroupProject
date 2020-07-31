package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobOfferController {

    @Autowired
    private JobOfferService jobOfferService;

    /**
     *
     * @return a list of all available job offers
     */
    @GetMapping("jobOffer")
    public List<JobOffer> getListOfJobOffers() {
        return jobOfferService.getJobOffers();
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
        return jobOfferService.updateJobOffer(jobOffer,jobOfferId);
    }
}
