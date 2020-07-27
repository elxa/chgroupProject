package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class match1Skill {

    @Autowired
    private ApplicantSkillService applicantSkillService;

    @PostMapping("applicant/{applicantId}/skill/{skillId}")
    public ApplicantSkill addApplicantSkill(@PathVariable int applicantId,
                                            @PathVariable int skillId)
    //TODO throws ApplicntCreationException, SkillCreationException
    {
        return applicantSkillService.addApplicantSkill(applicantId,skillId);
    }
}
