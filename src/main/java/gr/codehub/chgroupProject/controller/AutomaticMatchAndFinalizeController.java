package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.matcher.CreateManualMatchService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import gr.codehub.chgroupProject.service.matcher.AutomaticMatchServiceImpl;
import gr.codehub.chgroupProject.service.matcher.FinalizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutomaticMatchAndFinalizeController {

    @Autowired
    private ApplicantSkillService applicantSkillService;
    @Autowired
    private JobOfferSkillService jobOfferSkillService;

    @Autowired
    private CreateManualMatchService createManualMatchService;

    @Autowired
    private AutomaticMatchServiceImpl automaticMatchServiceImpl;

    @Autowired
    private FinalizeServiceImpl finalizeServiceImpl;


    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId) throws ApplicantNotFoundException, SkillNotFoundException {
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }

    //******************************************** JobOfferSkill Controller*********************************************
    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkillService(@PathVariable int jobOfferId, @PathVariable int skillId) throws SkillNotFoundException, JobOfferNotFoundException {
        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }

    @GetMapping ("matching")
    public List<CreateAndMatch> doMatch() throws ApplicantNotFoundException, JobOfferNotFoundException,
            SkillNotFoundException {
        return automaticMatchServiceImpl.DoAutomaticMatch();
    }

    @GetMapping("finalize/{createAndMatchId}")
    public CreateAndMatch finalizeMatch(@PathVariable int createAndMatchId) throws CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException {
            return finalizeServiceImpl.doFinalize(createManualMatchService.findCreateAndMatch(createAndMatchId));
    }

}
