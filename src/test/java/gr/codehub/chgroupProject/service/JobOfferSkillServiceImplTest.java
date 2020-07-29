package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JobOfferSkillServiceImplTest {
    @Autowired
    JobOfferService jb;

    @Autowired
    SkillService ss;

@Test
void addJobOfferSkill()
        throws SkillNotFoundException, JobOfferNotFoundException,
        JobOfferNotValidFields, SkillNotValidFields {
    List<JobOffer> jobOffers=new ArrayList<>();
    List<Skill> skills=new ArrayList<>();
    JobOffer jo1=new JobOffer();
    Skill ss1=new Skill();
    jo1.setId(1);
    ss1.setId(1);

    JobOffer jo2=new JobOffer();
    Skill ss2=new Skill();
    jo2.setId(2);
    ss2.setId(2);

    jobOffers.add(jb.addJobOffer(jo1));
    jobOffers.add(jb.addJobOffer(jo2));

    skills.add(ss.addSkill(ss1));
    skills.add(ss.addSkill(ss2));

    assertEquals(2, jobOffers.size());
    assertEquals(2, skills.size());


}
}
