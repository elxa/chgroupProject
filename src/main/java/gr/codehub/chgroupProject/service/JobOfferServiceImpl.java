package gr.codehub.chgroupProject.service;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO EXCHEPTION
//TODO updateJobOffer with excheption
//TODO updateJobOffer -> available
//todo exception if user does not put a field

@Service
@Slf4j
public class JobOfferServiceImpl implements JobOfferService {

    Logger logger = LoggerFactory.getLogger(JobOfferServiceImpl.class);

    @Autowired
    private JobOfferRepository jobOfferRepo;

    /**
     * A methods to view all the job offers
     * @return a list of all the available job offers
     */

    /**
     * A methods to view all the job offers
     * @return a list of all the available job offers
     */
    @Override
    public List<JobOffer> getJobOffers(String companyName, String region, String nameOfSkill) throws JobOfferNotFoundException {
        logger.info("Get a list of JobOffers from db");

        List<JobOffer> jobOffers = new ArrayList<>();

        if (companyName != null) {
            return jobOfferRepo.findCompanyName(companyName);
        }

        if (region != null) {
            return jobOfferRepo.findRegion(region);
        }
        if (nameOfSkill != null) {
            return jobOfferRepo.findListOfJobOfferWithSkillName(nameOfSkill);
        }
        return jobOfferRepo.findAll();
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

        jobOffer.setDateOfJobOffer(LocalDateTime.now());
        return jobOfferRepo.save(jobOffer);
    }

//    @Override
//    public JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId) {
//        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId).get();
////                .orElseThrow("sss"
////                     //   () -> new CustomerNotFoundException("not such customer")
////                              );
//        //se periptwsh pou den brei to id
//        jobOfferInDb.setPosition(jobOffer.getPosition());
//        jobOfferInDb.setRegion(jobOffer.getRegion());
//
//        boolean available = jobOfferInDb.isAvailable();
//        System.out.println("HHHHHHHHHHHHHHHHHHH" +available);
//        jobOfferInDb.setAvailable(available);
//
//        jobOfferRepo.save(jobOfferInDb);
//
//        return jobOfferInDb;
//
//    }

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

    public List<JobOffer> getJobOfferList() throws JobOfferNotFoundException {

        return jobOfferRepo.findAll();
    }


}


