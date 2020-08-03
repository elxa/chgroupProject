package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JobOfferServiceImpl implements JobOfferService {

    Logger logger = LoggerFactory.getLogger(JobOfferServiceImpl.class);

    @Autowired
    private JobOfferRepository jobOfferRepo;

    /**
     * A methods to view all the job offers
     *
     * @return a list of all the available job offers
     */
    public List<JobOffer> getJobOfferList() throws JobOfferNotFoundException {
        return jobOfferRepo.findAll();
    }

    /**
     * Return a list of job offer which depend on the parameters which user give in url
     *
     * @param companyName
     * @param region
     * @param nameOfSkill
     * @return
     * @throws JobOfferNotFoundException
     */
    @Override
    public List<JobOfferSkillDTO> getJobOffers(String companyName, String region, String nameOfSkill) throws JobOfferNotFoundException {
        logger.info("Get a list of JobOffers from db");

        if (companyName != null && region != null && nameOfSkill != null) {
            return jobOfferRepo.findJobOfferByCompanyNameRegionSkillName(companyName, region, nameOfSkill);
        }
        if (companyName != null && region != null && nameOfSkill == null) {
            return jobOfferRepo.findJobOfferByCompanyNameANDRegion(companyName, region);
        }
        if ((companyName != null || region != null) && nameOfSkill == null) {
            return jobOfferRepo.findJobOfferByCompanyNameOrRegion(companyName, region);
        }
        if (companyName != null && nameOfSkill != null) {
            return jobOfferRepo.findJobOfferByCompanyNameAndSkillName(companyName, nameOfSkill);
        }
        if (region != null && nameOfSkill != null) {
            return jobOfferRepo.findJobOfferByRegionAndSkillName(region, nameOfSkill);
        }
        if (nameOfSkill != null && companyName == null && region == null) {
            return jobOfferRepo.jobOfferRepo(nameOfSkill);
        }
        logger.info("Return a list of applicants");
        return jobOfferRepo.jobOfferListDTO();
    }

    /**
     * A method to update the existing job offer list
     * @param jobOffer
     * @param jobOfferId
     * @return a list with the updated job offer list
     * @throws JobOfferNotFoundException
     */
    @Override
    public JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId) throws JobOfferNotFoundException {
        logger.info("Update a job offer");
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId)
                .orElseThrow(
                        () -> new JobOfferNotFoundException("Job Offer Not Found"));

        jobOfferInDb.setAvailable(jobOffer.getAvailable());
        jobOfferRepo.save(jobOfferInDb);

        return jobOfferInDb;
    }

    /**
     * A method to add a new job offer
     * @param jobOffer
     * @return the new job offer list with the added job offer
     * @throws JobOfferNotFoundException
     * @throws JobOfferNotValidFields
     */
    @Override
    public JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields {
        logger.info("Add a job offer in db");
        if(jobOffer == null){
            throw new JobOfferNotFoundException("Job Offer Not found");
        }
        if( StringUtils.isEmpty(jobOffer.getCompanyName())
                || StringUtils.isEmpty(jobOffer.getPosition())
                || StringUtils.isEmpty(jobOffer.getRegion())
                || StringUtils.isEmpty(jobOffer.getLevel())
                || StringUtils.isEmpty(jobOffer.getAvailable())
        ) //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
            throw new JobOfferNotValidFields("Job Offer fields must not be null");

        jobOffer.setDateOfJobOffer(LocalDate.now());
        return jobOfferRepo.save(jobOffer);
    }

    /**
     * A method that get the job offer by Id
     * @param jobOfferId
     * @return job0ffer
     * @throws JobOfferNotFoundException
     */
    @Override
    public JobOffer getJobOfferById(int jobOfferId) throws JobOfferNotFoundException{
        logger.info("Get Job Offer By Id ");
        Optional<JobOffer> oJobOffer= jobOfferRepo.findById(jobOfferId);
        if (oJobOffer.isPresent()){ //ean uparxei epistrefei to jobOffer
            return oJobOffer.get();
        }
        else throw new JobOfferNotFoundException("Job Offer Not Found");
    }

}


