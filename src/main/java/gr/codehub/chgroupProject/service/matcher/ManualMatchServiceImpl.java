package gr.codehub.chgroupProject.service.matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.CreateAndMatchRepository;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManualMatchServiceImpl implements ManualMatchService {
    Logger logger = LoggerFactory.getLogger(ManualMatchServiceImpl.class);

    @Autowired
    private CreateAndMatchRepository camRepo;
    @Autowired
    private ApplicantRepository applicantRepo;
    @Autowired
    private JobOfferRepository jobOfferRepo;
    @Autowired
    private CreateAndMatchRepository createAndMatchRepo;

    /**
     * A list of manual matches
     *
     * @return a list of manual matches
     */
    @Override
    public List<CreateAndMatch> getManualMatches() {
        logger.info("A list of manual matches");
        return createAndMatchRepo.listOfManualCreateAndMatch();
    }


    /**
     * Add Create and match
     *
     * @param applicantId
     * @param jobOfferId
     * @return
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     */
    @Override
    public CreateAndMatch addCreateAndMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException {
        logger.info("Add manual Create and match");

        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        () -> new ApplicantNotFoundException("Applicant Not Found")
                );
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId)
                .orElseThrow(
                        () -> new JobOfferNotFoundException("Job Offer Not Found")
                );
        CreateAndMatch createAndMatch = new CreateAndMatch();
        createAndMatch.setDom(LocalDateTime.now());
        createAndMatch.setApplicant(applicantInDb);
        createAndMatch.setJobOffer(jobOfferInDb);
        createAndMatch.setManualMatch(false);//to bazoume false giati den einai manual match
        createAndMatch.setFinalized(false);
        return camRepo.save(createAndMatch);
    }

    /**
     * Add manual matches
     *
     * @param applicantId
     * @param jobOfferId
     * @return
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     */
    @Override
    public CreateAndMatch addCreateAndMatchManual(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException {
        logger.info("Add manual matches");
        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        () -> new ApplicantNotFoundException("Applicant Not Found")
                );
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId)
                .orElseThrow(
                        () -> new JobOfferNotFoundException("Job Offer Not Found")
                );
        CreateAndMatch createAndMatch = new CreateAndMatch();
        createAndMatch.setDom(LocalDateTime.now());
        createAndMatch.setApplicant(applicantInDb);
        createAndMatch.setJobOffer(jobOfferInDb);
        createAndMatch.setManualMatch(true);//epeidh einai manual to bazoume true
        createAndMatch.setFinalized(false);
        return camRepo.save(createAndMatch);
    }

    /**
     * Update a manual match
     *
     * @param createAndMatch
     * @param createAndMatchId
     * @return
     * @throws CreateAndMatchNotFound
     */
    @Override
    public CreateAndMatch updateCreateAndMatch(CreateAndMatch createAndMatch, int createAndMatchId) throws CreateAndMatchNotFound {
        logger.info("Update a manual matches");
        CreateAndMatch createAndMatchInDb = camRepo.findById(createAndMatchId)
                .orElseThrow(
                        () -> new CreateAndMatchNotFound("Match Not Found"));
        createAndMatchInDb.setFinalized(createAndMatch.getFinalized());
        return camRepo.save(createAndMatchInDb);
    }

    /**
     * Check if manual match exists
     *
     * @param applicant
     * @param jobOffer
     * @return
     */
    @Override
    public boolean checkIfApplicantIdAndJobOfferIdExist(Applicant applicant, JobOffer jobOffer) {
        logger.info("Check If Applicant Id And Job Offer Id Exist");
        Optional<CreateAndMatch> createAndMatchInDb = camRepo.findCreateAndMatchByJobOfferAndApplicant(jobOffer, applicant);
        if (createAndMatchInDb.isPresent()) {
            return true;
        } else
            return false;
    }

    /**
     * Delete a manual matches
     *
     * @param createAndMatchManualId
     * @return
     * @throws CreateAndMatchNotFound
     */

    @Override
    public boolean deleteCreateAndMatch(int createAndMatchManualId) throws CreateAndMatchNotFound {
        Optional<CreateAndMatch> oCreateAndMatch = camRepo.findById(createAndMatchManualId);
        logger.info("Delete a manual matches");
        if (oCreateAndMatch.isPresent()) {
            CreateAndMatch createAndMatch = oCreateAndMatch.get();
            if (createAndMatch.getManualMatch() == true) {
                camRepo.deleteById(createAndMatch.getId());
                return true;
            }
        } else throw new CreateAndMatchNotFound("Match Not Found");
        return false;
    }

    /**
     * Find a manual match
     *
     * @param createAndMatch
     * @return
     * @throws CreateAndMatchNotFound
     */
    @Override
    public CreateAndMatch findCreateAndMatch(int createAndMatch) throws CreateAndMatchNotFound {
        logger.info("Find a manual match");
        Optional<CreateAndMatch> oCreateAndMatch = camRepo.findById(createAndMatch);
        if (oCreateAndMatch.isPresent()) {
            return oCreateAndMatch.get();
        } else throw new CreateAndMatchNotFound("Match Not Found");
    }
}