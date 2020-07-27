package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.excheption.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.List;

public interface JobOfferService {
    List<JobOffer> getJobOffers();
    JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields; //throws CustomerCreationException; //gia na epibebaiwsoume oti autos o customer mphke
    JobOffer getJobOfferById(int jobOfferId) throws JobOfferNotFoundException; //throws CustomerNotFoundException;//to exception auto anaferetai sto otan den briskei ton pelath

}
