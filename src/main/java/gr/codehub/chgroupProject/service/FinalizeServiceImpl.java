package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizeServiceImpl implements FinalizeService {

    private CreateManualMatchService createManualMatchService;
    private ApplicantService applicantService;
    private JobOfferService jobOfferService;

    private AutomaticMatchServiceImpl automaticMatchServiceImpl;

    @Autowired
    public FinalizeServiceImpl(CreateManualMatchService createManualMatchService, ApplicantService applicantService,
                               JobOfferService jobOfferService, AutomaticMatchServiceImpl automaticMatchServiceImpl)
            throws JobOfferNotFoundException, ApplicantNotFoundException, SkillNotFoundException {

        this.createManualMatchService = createManualMatchService;
        this.applicantService = applicantService;
        this.jobOfferService = jobOfferService;

        this.automaticMatchServiceImpl = automaticMatchServiceImpl;
    }


    @Override
    public CreateAndMatch doFinalize(CreateAndMatch createAndMatch) throws
            CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException {
        Applicant applicant = createAndMatch.getApplicant();
        JobOffer jobOffer = createAndMatch.getJobOffer();

        if (createAndMatch.getFinalized() == false) {
            if (applicant.getAvailable() && jobOffer.getAvailable()) {
                createAndMatch.setFinalized(true);
                createManualMatchService.updateCreateAndMatch(createAndMatch, createAndMatch.getId());

                applicant.setAvailable(false);
                applicantService.updateApplicant(applicant, applicant.getId());

                jobOffer.setAvailable(false);
                jobOfferService.updateJobOffer(jobOffer, jobOffer.getId());


                return createAndMatch;
            } else throw new CreateAndMatchNotFound("IT IS NOT AVAILABLE");

        } else throw new CreateAndMatchNotFound("IT IS NOT AVAILABLE");
    }
}
