package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.List;

public interface JobOfferService {
    List<JobOffer> getJobOffers();
    JobOffer addJobOffer(JobOffer jobOffer); //throws CustomerCreationException; //gia na epibebaiwsoume oti autos o customer mphke
    JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId);
           // throws CustomerNotFoundException;

    boolean deleteJobOffer(int jobOfferId);
    JobOffer getJobOffer(int jobOfferId); //throws CustomerNotFoundException;//to exception auto anaferetai sto otan den briskei ton pelath

}
