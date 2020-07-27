package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.repository.JobOfferSkillRepository;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
public class matchSkill {

    @Autowired
    private JobOfferSkillService jobOfferSkillService;

    @PostMapping("jobOffer/{jobOfferId}/skill/{skillId}")
    public JobOfferSkill JobOfferSkillService(@PathVariable int jobOfferId, @PathVariable int skillId) {

        return jobOfferSkillService.addJobOfferSkill(jobOfferId, skillId);
    }
}
