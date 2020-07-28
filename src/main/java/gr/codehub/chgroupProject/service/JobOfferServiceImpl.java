package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.excheption.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<JobOffer> getJobOffers() {
        return jobOfferRepo.findAll();
    }

    @Override
    public JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields {
        if(jobOffer == null){
            throw new JobOfferNotFoundException("Job Offer Not found");
        }
        if(jobOffer.getPosition() ==null|| jobOffer.getRegion() == null ||
                jobOffer.getPosition().equals("") || jobOffer.getRegion().equals("")){ //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
            throw new JobOfferNotValidFields("Job Offer fields must not be null");
        }

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
        Optional<JobOffer> oJobOffer= jobOfferRepo.findById(jobOfferId);
        if (oJobOffer.isPresent()){ //ean uparxei epistrefei to jobOffer
            return oJobOffer.get();
        }
        else throw new JobOfferNotFoundException("Job Offer Not Found");
    }
}


