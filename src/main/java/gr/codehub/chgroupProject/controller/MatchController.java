package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
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

    /**
     *
     * @param applicantId
     * @param skillId
     * @return the new added applicantSkill with the applicantId and the skillId
     * @throws ApplicantNotFoundException
     * @throws SkillNotFoundException
     */

    //******************************************** ApplicantSkill Controller********************************************
    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId, @PathVariable int skillId)
            throws ApplicantNotFoundException, SkillNotFoundException {
        return applicantSkillService.addApplicantSkill(applicantId, skillId);
    }

    /**
     *
     * @param jobOfferId
     * @param skillId
     * @return the new job offer added with the skillId and the jobOfferId
     * @throws SkillNotFoundException
     * @throws JobOfferNotFoundException
     */

    //******************************************** JobOfferSkill Controller*********************************************
    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkillService(@PathVariable int jobOfferId, @PathVariable int skillId)
            throws SkillNotFoundException, JobOfferNotFoundException {
        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }
}
