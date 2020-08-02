package gr.codehub.chgroupProject.service.matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.RetrieveDatesException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.CreateAndMatchRepository;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.JobOfferService;
import gr.codehub.chgroupProject.util.DateValidator;
import gr.codehub.chgroupProject.util.DateValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FinalizeServiceImpl implements FinalizeService {

    Logger logger = LoggerFactory.getLogger(FinalizeServiceImpl.class);

    @Autowired
    private ManualMatchService manualMatchService;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private CreateAndMatchRepository createAndMatchRepo;
    @Autowired
    private AutomaticMatchServiceImpl automaticMatchServiceImpl;
    @Autowired
    private DateValidator dateValidator;

    /**
     * Take a match and make it fianalize
     *
     * @param createAndMatch take a match which find it by id
     * @return the finalized mathe
     * @throws CreateAndMatchNotFound
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     */
    @Override
    public CreateAndMatch doFinalize(CreateAndMatch createAndMatch) throws CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException {
        logger.info("Take a match and make it fianalize");

        Applicant applicant = createAndMatch.getApplicant();
        JobOffer jobOffer = createAndMatch.getJobOffer();

        if (createAndMatch.getFinalized() == false) {
            if (applicant.getAvailable() && jobOffer.getAvailable()) {
                createAndMatch.setFinalized(true);
                createAndMatch.setDom(LocalDateTime.now());
                manualMatchService.updateCreateAndMatch(createAndMatch, createAndMatch.getId());

                applicant.setAvailable(false);
                applicantService.updateApplicant(applicant, applicant.getId());

                jobOffer.setAvailable(false);
                jobOfferService.updateJobOffer(jobOffer, jobOffer.getId());


                return createAndMatch;
            } else throw new CreateAndMatchNotFound("IT IS NOT AVAILABLE");

        } else throw new CreateAndMatchNotFound("IT IS NOT AVAILABLE");
    }

    /**
     * A list of finalized create and matches
     *
     * @return a list of finalized create and matches
     */
    @Override
    public List<CreateAndMatch> finalizedList() {
        logger.info("A list of finalized create and matches");
        return createAndMatchRepo.finalizedList();
    }

    /**
     * a list of finalized records in specific range of dates
     *
     * @param startDate the first date which you want to retrieve records
     * @param endDate   the last date which you want to retrieve records
     * @return a list of finalized records in specific range of dates
     */
    @Override
    public List<CreateAndMatch> weeklyAndMonthlyRecords(String startDate, String endDate) throws RetrieveDatesException {
        logger.info("A list of weekly and monthly records");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        DateValidator validator = new DateValidatorImpl(dateFormatter);

         //if(startDate !=null && endDate != null){
       if ((startDate != null && endDate != null) &&
      //  if (    (validator.isValid(startDate) == true && validator.isValid(endDate) == true)) { //elegxoume e
                (validator.isValid(startDate) == true && validator.isValid(endDate) == true)) { //elegxoume e
            LocalDate startDateLoc = LocalDate.parse(startDate);
            LocalDate endDateLoc = LocalDate.parse(endDate);
           System.out.println(startDateLoc +"*******************************"+endDateLoc);
            return createAndMatchRepo.weeklyAndMonthlyRecords(startDateLoc, endDateLoc);
        } else
            throw new RetrieveDatesException("You  give wrong format for the dates OR You dan't give the dates which you want to retieve the records\"");
       //  }else throw new RetrieveDatesException("You dan't give the dates which you want to retieve the records");
    }
}
