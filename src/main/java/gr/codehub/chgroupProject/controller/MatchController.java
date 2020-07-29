package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.CreateAndMatchService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    private ApplicantSkillService applicantSkillService;
    @Autowired
    private JobOfferSkillService jobOfferSkillService;
    @Autowired
    private CreateAndMatchService createAndMatchService;


    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId)
    //TODO throws ApplicntCreationException, SkillCreationException
    {
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }

    //******************************************** JobOfferSkill Controller*********************************************
    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkillService(@PathVariable int jobOfferId, @PathVariable int skillId) {

        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }

    //******************************************** MatchSkill Controller*********************************************
//    @PostMapping("applicant/{applicantId}/jobOffer/{jobOfferId}")
//    public CreateAndMatch createAndMatchService(@PathVariable int applicantId, @PathVariable int jobOfferId){
//
//        return
//    }

    @GetMapping("dokimh")
    public void JobOffer() throws JobOfferNotFoundException {

        createAndMatchService.autoCreateMatch();
    }
}
