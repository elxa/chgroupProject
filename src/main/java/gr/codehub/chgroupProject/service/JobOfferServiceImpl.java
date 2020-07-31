package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

//TODO EXCHEPTION
//TODO updateJobOffer with excheption
//TODO updateJobOffer -> available
//todo exception if user does not put a field

@Service
public class JobOfferServiceImpl implements JobOfferService{

    @Autowired
    private JobOfferRepository jobOfferRepo;

    /**
     * A methods to view all the job offers
     * @return a list of all the available job offers
     */


    @Override
    public List<JobOffer> getJobOffers() {
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
    public JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId)
            throws JobOfferNotFoundException {
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
    public JobOffer addJobOffer(JobOffer jobOffer)
            throws JobOfferNotFoundException, JobOfferNotValidFields {
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
        Optional<JobOffer> oJobOffer= jobOfferRepo.findById(jobOfferId);
        if (oJobOffer.isPresent()){ //ean uparxei epistrefei to jobOffer
            return oJobOffer.get();
        }
        else throw new JobOfferNotFoundException("Job Offer Not Found");
    }

    @Override
    public void addJobOfferSkill() {

    }
}


