package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.repository.JobOfferSkillRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobOfferSkillServiceImplTest {
    @Mock
    private JobOfferSkillRepository jobOfferSkillRepository;

    @InjectMocks
    private JobOfferService jobOfferService = new JobOfferServiceImpl();

    @Autowired
    private JobOfferService jobOfferService1;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobOfferSkillService jobOfferSkillService;

    @Test
    void addJobOfferSkill() throws
            JobOfferNotFoundException,
            JobOfferNotValidFields, SkillNotFoundException, SkillNotValidFields {
//        JobOffer jobOffer=new JobOffer();
//        jobOffer.setPosition("java");
//        jobOffer.setAvailable(true);
//        jobOffer.setRegion("athens");
//        jobOffer.setCompanyName("Accenture");
//        jobOffer.setLevel("mid");
//
//        jobOfferService.addJobOffer(jobOffer);
//        Skill skill=new Skill();
//        skill.setNameOfSkill("java");
//
//        skillService.addSkill(skill);
//
//        JobOfferSkill jobOfferSkill1 = new JobOfferSkill();
//
//        when(jobOfferSkillRepository.save(jobOfferSkill1)).thenReturn(jobOfferSkill1);
//        assertNotNull(jobOfferSkill1);
//        assertEquals(jobOfferSkill1.getId(), jobOfferSkill1.getId());
//    }
}}


