package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import gr.codehub.chgroupProject.repository.JobOfferSkillRepository;
import gr.codehub.chgroupProject.repository.SkillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class JobOfferSkillServiceImplTest {
    @Mock
    private JobOfferSkillRepository jobOfferSkillRepository;
    @Mock
    private JobOfferRepository jobOfferRepository;
    @Mock
    private SkillRepository skillRepository;
    @InjectMocks
    private JobOfferSkillService jobOfferSkillService = new JobOfferSkillServiceImpl();

    @Test
    void addJobOfferSkill() throws SkillNotFoundException, JobOfferNotFoundException {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1);
        jobOffer.setAvailable(true);
        jobOffer.setLevel("Mid");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setPosition("IT");
        jobOffer.setRegion("Athens");
        Skill skill = new Skill();
        skill.setId(1);
        skill.setNameOfSkill("Java");
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(jobOffer));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        JobOfferSkill jobOfferSkill = jobOfferSkillService.addJobOfferSkill(1, 1);
        when(jobOfferSkillRepository.save(jobOfferSkill)).thenReturn(jobOfferSkill);
        assertEquals(1, jobOfferSkill.getJobOffer().getId());
        assertEquals(1, jobOfferSkill.getSkill().getId());
    }

    @Test
    void addJobOfferSkillJobOfferNotFound() {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1);
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        Skill skill = new Skill();
        skill.setId(1);
        skill.setNameOfSkill("java");
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(jobOffer));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            jobOfferSkillService.addJobOfferSkill(2, 1);
        });
    }

    @Test
    void addJobOfferSkillSkillNotFound() {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1);
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        Skill skill = new Skill();
        skill.setId(1);
        skill.setNameOfSkill("java");
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(jobOffer));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        Assertions.assertThrows(SkillNotFoundException.class, () -> {
            jobOfferSkillService.addJobOfferSkill(1, 2);
        });
    }
}


