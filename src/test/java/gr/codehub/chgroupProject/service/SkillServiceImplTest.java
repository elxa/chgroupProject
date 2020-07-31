package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.SkillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService = new SkillServiceImpl();


    @Test
    void getSkill() {
        Skill skill1 = new Skill();
        skill1.setId(1);
        skill1.setNameOfSkill("java");
        List<Skill> skills = Arrays.asList(skill1);
        when(skillRepository.findAll()).thenReturn(skills);
        List<Skill> skillsRetrieved = skillService.getAllSkills();
        assertEquals(1, skillsRetrieved.size());
    }


    @Test
    void addSkill() throws SkillNotFoundException, SkillNotValidFields {
        Skill skill1 = new Skill();
        skill1.setNameOfSkill("java");
        when(skillRepository.save(skill1)).thenReturn(skill1);
        Skill skill = skillService.addSkill(skill1);
        assertNotNull(skill);
        assertEquals(skill1.getNameOfSkill(), skill.getNameOfSkill());
    }


    @Test
    void addSkillNotFoundException() {
        Skill s1 = null;
        Assertions.assertThrows(SkillNotFoundException.class, () -> {
            skillService.addSkill(s1);
        });
    }

    @Test
    void addSkillNotValidFieldsException() {
        Skill skill1 = new Skill();
        skill1.setId(1);
        skill1.setNameOfSkill("");
        Assertions.assertThrows(SkillNotValidFields.class, () -> {
            skillService.addSkill(skill1);
        });
    }


    @Test
    void deleteSkill() throws SkillNotFoundException, SkillNotValidFields {
        Skill ss1 = new Skill();
        ss1.setNameOfSkill("Angular");
        ss1.setId(1);

        skillService.addSkill(ss1);

        assertEquals(true, skillService.deleteSkill((1)));
    }

    @Test
    void getSkillById() throws SkillNotValidFields, SkillNotFoundException {
        Skill skill1 = new Skill();
        skill1.setId(1);
        skill1.setNameOfSkill("java");
        Optional<Skill> optionalSkill = Optional.of(skill1);
        when(skillRepository.findById(1)).thenReturn(optionalSkill);
        Skill sk = skillService.getSkillById(1);
        assertEquals(1, sk.getId());
    }

    @Test
    void getSkillByIdSkillNotFound() {
        when(skillRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(SkillNotFoundException.class, () -> {
            skillService.getSkillById(1);
        });
    }

    @Test
    void getSkillByName() throws SkillNotFoundException, SkillNotValidFields {
        Skill skill = new Skill();
        skill.setId(1);
        skill.setNameOfSkill("java");
        skillService.addSkill(skill);
        assertThat(skillService.findSkillByName("java"));
    }

    @Test
    void updateSkill() throws SkillNotFoundException {
        Skill skill = new Skill();
        skill.setId(1);
        skill.setNameOfSkill("web");
        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));
        Skill skillDB = skillService.updateSkill(skill, 1);
        assertEquals("web", skillDB.getNameOfSkill());
        skill.setNameOfSkill("java");
        skillDB = skillService.updateSkill(skill, 1);
        assertEquals("java", skillDB.getNameOfSkill());


    }
}




