package gr.codehub.chgroupProject.service.Matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.CreateAndMatchRepository;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinalizeServiceImpl implements FinalizeService {

    private ManualMatchService manualMatchService;
    private ApplicantService applicantService;
    private JobOfferService jobOfferService;
    private CreateAndMatchRepository createAndMatchRepo;

    private AutomaticMatchServiceImpl automaticMatchServiceImpl;

    @Autowired
    public FinalizeServiceImpl(ManualMatchService manualMatchService,
                               ApplicantService applicantService,
                               JobOfferService jobOfferService,
                               CreateAndMatchRepository createAndMatchRepo,
                               AutomaticMatchServiceImpl automaticMatchServiceImpl) {
        this.manualMatchService = manualMatchService;
        this.applicantService = applicantService;
        this.jobOfferService = jobOfferService;
        this.createAndMatchRepo = createAndMatchRepo;
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

    @Override
    public List<CreateAndMatch> finalizedList() {
        return createAndMatchRepo.finalizedList();
    }

}
