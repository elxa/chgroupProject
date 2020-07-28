package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private ApplicantSkillService applicantSkillService;
    @Autowired
    private JobOfferSkillService jobOfferSkillService;


    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId) throws SkillNotFoundException

    {
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }

    //******************************************** JobOfferSkill Controller*********************************************
    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkillService(@PathVariable int jobOfferId, @PathVariable int skillId) {

        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }
}
