package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.ApplicantSkillRepository;
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
class ApplicantSkillServiceImplTest {


    @Mock
    private ApplicantSkillRepository applicantSkillRepository;
    @Mock
    private ApplicantRepository applicantRepository;
    @Mock
    private SkillRepository skillRepository;
    @InjectMocks
    private ApplicantSkillService applicantSkillService = new ApplicantSkillServiceImpl();


    @Test
    void addApplicantSkillSuccessful()
            throws ApplicantNotFoundException, SkillNotFoundException {
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setFirstName("Kerk");
        applicant.setLastName("Kolia");
        applicant.setAvailable(true);
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setAddress("Kolokotroni 1");
        applicant.setLevel("Junior");

        Skill skill=new Skill();
        skill.setId(1);
        skill.setNameOfSkill("java");
        when(applicantRepository.findById(1)).thenReturn(Optional.of(applicant));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        ApplicantSkill applicantSkill = applicantSkillService.addApplicantSkill(1,1);
        when(applicantSkillRepository.save(applicantSkill)).thenReturn(applicantSkill);
        assertEquals(1,applicantSkill.getApplicant().getId());
        assertEquals(1,applicantSkill.getSkill().getId());

    }

    @Test
    void addApplicantSkillApplicantNotFound() {
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setFirstName("Kerk");
        applicant.setLastName("Kolia");
        applicant.setAvailable(true);
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setAddress("Kolokotroni 1");
        applicant.setLevel("Junior");

        Skill skill=new Skill();
        skill.setId(1);
        skill.setNameOfSkill("java");
        when(applicantRepository.findById(1)).thenReturn(Optional.of(applicant));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            applicantSkillService.addApplicantSkill(2,1);
        });

    }

    @Test
    void addApplicantSkillSkillNotFound() {
        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setFirstName("Kerk");
        applicant.setLastName("Kolia");
        applicant.setAvailable(true);
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setAddress("Kolokotroni 1");
        applicant.setLevel("Junior");

        Skill skill=new Skill();
        skill.setId(1);
        skill.setNameOfSkill("java");
        when(applicantRepository.findById(1)).thenReturn(Optional.of(applicant));
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        Assertions.assertThrows(SkillNotFoundException.class, () -> {
            applicantSkillService.addApplicantSkill(1,2);
        });

    }
}