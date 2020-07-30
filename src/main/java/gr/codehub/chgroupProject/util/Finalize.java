package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.CreateAndMatchRepository;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.CreateAndMatchService;
import gr.codehub.chgroupProject.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Finalize {

    private CreateAndMatchService createAndMatchService;
    private ApplicantService applicantService;
    private JobOfferService jobOfferService;

    private AutomaticMatch automaticMatch;

    @Autowired
    public Finalize(CreateAndMatchService createAndMatchService, ApplicantService applicantService,
                    JobOfferService jobOfferService, AutomaticMatch automaticMatch)
            throws JobOfferNotFoundException, ApplicantNotFoundException, SkillNotFoundException {

        this.createAndMatchService = createAndMatchService;
        this.applicantService = applicantService;
        this.jobOfferService = jobOfferService;

        this.automaticMatch = automaticMatch;
    }


   // List<CreateAndMatch> currentMatches = automaticMatch.DoAutomaticMatch();

    public CreateAndMatch doFinalize(CreateAndMatch createAndMatch) throws CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException {
        Applicant applicant = createAndMatch.getApplicant();
        JobOffer jobOffer = createAndMatch.getJobOffer();

//        if(!currentMatches.contains(createAndMatch))
//                throw new CreateAndMatchNotFound("Match not Found");
//        else {
            createAndMatch.setAvailable(false);
            createAndMatchService.updateCreateAndMatch(createAndMatch, createAndMatch.getId());

            applicant.setAvailable(false);
            applicantService.updateApplicant(applicant,applicant.getId());

            jobOffer.setAvailable(false);
            jobOfferService.updateJobOffer(jobOffer, jobOffer.getId());

            return createAndMatch ;

       // }
    }
}
