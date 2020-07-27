package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO EXCHEPTION
//TODO updateJobOffer with excheption
//TODO updateJobOffer -> available

@Service
public class JobOfferServiceImpl implements JobOfferService{

    @Autowired
    private JobOfferRepository jobOfferRepo;

    @Override
    public List<JobOffer> getJobOffers() {
        return jobOfferRepo.findAll();
    }

    @Override
    public JobOffer addJobOffer(JobOffer jobOffer) {
        if(jobOffer == null){
         //   throw new CustomerCreationException("null job Offer");
        }
//        if( customer.getEmail()==null || !customer.getEmail().contains("@")){ //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
//            throw new CustomerCreationException("invalid ffff");
//        }
        return jobOfferRepo.save(jobOffer);

    }

    @Override
    public JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId) {
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId).get();
//                .orElseThrow("sss"
//                     //   () -> new CustomerNotFoundException("not such customer")
//                              );
        //se periptwsh pou den brei to id
        jobOfferInDb.setPosition(jobOffer.getPosition());
        jobOfferInDb.setRegion(jobOffer.getRegion());

        boolean available = jobOfferInDb.isAvailable();
        System.out.println("HHHHHHHHHHHHHHHHHHH" +available);
        jobOfferInDb.setAvailable(available);

        jobOfferRepo.save(jobOfferInDb);

        return jobOfferInDb;

    }


    @Override
    public JobOffer getJobOffer(int jobOfferId) throws JobOfferNotFoundException{
        Optional<JobOffer> oJobOffer= jobOfferRepo.findById(jobOfferId);
        if (oJobOffer.isPresent()){
            return oJobOffer.get();
        }
        else throw new JobOfferNotFoundException("Job Offer Not Found");
  // }
//        return oJobOffer.get();//todo einai gia dior8wsh
    }
}
