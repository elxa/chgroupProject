package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.excheption.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JobOfferServiceImpl implements JobOfferService{

    @Autowired
    private JobOfferRepository jobOfferRepo;

    @Override
    public List<JobOffer> getJobOffers() {
        return jobOfferRepo.findAll();
    }

    @Override
    public JobOffer updateJobOffer(JobOffer jobOffer, int jobOfferId) throws JobOfferNotFoundException {
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId)
                .orElseThrow(
                        () -> new JobOfferNotFoundException("Job Offer Not Found"));

        jobOfferInDb.setAvailable(jobOffer.getAvailable());
        jobOfferRepo.save(jobOfferInDb);

        return jobOfferInDb;
    }

    @Override
    public JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException, JobOfferNotValidFields {
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
        return jobOfferRepo.save(jobOffer);
    }


    @Override
    public JobOffer getJobOfferById(int jobOfferId) throws JobOfferNotFoundException{
        Optional<JobOffer> oJobOffer= jobOfferRepo.findById(jobOfferId);
        if (oJobOffer.isPresent()){ //ean uparxei epistrefei to jobOffer
            return oJobOffer.get();
        }
        else throw new JobOfferNotFoundException("Job Offer Not Found");
    }
}


