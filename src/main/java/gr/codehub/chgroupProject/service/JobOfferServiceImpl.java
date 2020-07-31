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


//    @Override
//    public List<JobOffer> getJobOffers() throws JobOfferNotFoundException {
//        return jobOfferRepo.findAll();
//    }

    @Override
    public List<JobOffer> getJobOffers(String companyName, String region, String nameOfSkill, String dateOfRegister) throws JobOfferNotFoundException {
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
        if (dateOfRegister != null) {
            return jobOfferRepo.findJobOfferListtWithDateOfRegister();
        }
        return jobOfferRepo.findAll();
    }

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

    @Override
    public JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields {
        logger.info("Add a job offer in db");
        if(jobOffer == null){
            throw new JobOfferNotFoundException("Job Offer Not found");
        }
        if( jobOffer.getCompanyName().equals("") || jobOffer.getCompanyName().equals("")
                || jobOffer.getPosition().equals("") || jobOffer.getPosition().equals("")
                || jobOffer.getRegion().equals("") || jobOffer.getRegion().equals("")
                || jobOffer.getLevel().equals("") || jobOffer.getLevel().equals("")
                || jobOffer.getAvailable().equals("") || jobOffer.getAvailable().equals("")){ //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
            throw new JobOfferNotValidFields("Job Offer fields must not be null");
        }
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


    @Override
    public JobOffer getJobOfferById(int jobOfferId) throws JobOfferNotFoundException{
        logger.info("Get Job Offer By Id ");
        Optional<JobOffer> oJobOffer= jobOfferRepo.findById(jobOfferId);
        if (oJobOffer.isPresent()) { //ean uparxei epistrefei to jobOffer
            return oJobOffer.get();
        } else throw new JobOfferNotFoundException("Job Offer Not Found");
    }

}

