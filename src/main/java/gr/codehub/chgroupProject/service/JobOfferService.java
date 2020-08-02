package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.List;

public interface JobOfferService {
    List<JobOffer> getJobOfferList() throws JobOfferNotFoundException;

    List<JobOfferSkillDTO> getJobOffers(String companyName, String region, String nameOfSkill) throws JobOfferNotFoundException;

    JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId) throws JobOfferNotFoundException;

    JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields; //throws CustomerCreationException; //gia na epibebaiwsoume oti autos o customer mphke

    JobOffer getJobOfferById(int jobOfferId) throws JobOfferNotFoundException; //throws CustomerNotFoundException;//to exception auto anaferetai sto otan den briskei ton pelath
}
