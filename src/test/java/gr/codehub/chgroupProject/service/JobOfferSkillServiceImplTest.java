package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.model.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JobOfferSkillServiceImplTest {
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobOfferSkillService jobOfferSkillService;

    @Test
    void addJobOfferSkill() throws
            SkillNotFoundException, JobOfferNotFoundException, JobOfferNotValidFields, SkillNotValidFields {
        JobOffer jobOffer=new JobOffer();
        jobOffer.setPosition("java");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("mid");

        jobOfferService.addJobOffer(jobOffer);
        Skill skill=new Skill();
        skill.setNameOfSkill("java");

        skillService.addSkill(skill);

        JobOfferSkill jobOfferSkill = new JobOfferSkill();


        jobOfferSkill = jobOfferSkillService.addJobOfferSkill(jobOffer.getId(), skill.getId());
        assertEquals(1, jobOfferSkill.getId());

    }
}


