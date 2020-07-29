package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SkillServiceImplTest {
@Autowired
    private SkillService skillService;
    @Test
    void getSkill() throws SkillNotValidFields, SkillNotFoundException{
        List<Skill> skill1=skillService.getAllSkills();
        assertEquals(0, skill1.size());
        Skill skill=new Skill();
        skill.setNameOfSkill("java");
    }
    @Test
    void addSkill() throws SkillNotFoundException, SkillNotValidFields{
        Skill s1=new Skill();
        s1.setNameOfSkill("Databases");
        Skill s2=new Skill();
        s2.setNameOfSkill("web");
        skillService.addSkill(s1);
        skillService.addSkill(s2);
        List<Skill> skill2=skillService.getAllSkills();
        assertEquals(2, skill2.size());
    }
    @Test
    void deleteSkill() throws SkillNotFoundException, SkillNotValidFields {
        Skill ss1 = new Skill();
        ss1.setNameOfSkill("Angular");
        ss1.setId(1);

        skillService.addSkill(ss1);

        assertEquals(true , skillService.deleteSkill((1)));
    }

    @Test
    void getSkillById() throws SkillNotValidFields, SkillNotFoundException{
        Skill skill=new Skill();
        skill.setNameOfSkill("java");
        skillService.addSkill(skill);
        int id=1;
        Skill ss=skillService.getSkillById(1);
        assertThat(ss.getId()).isEqualTo(id);
    }

    @Test
    void getSkillByName() throws SkillNotFoundException, SkillNotValidFields {
        Skill skill=new Skill();
        skill.setNameOfSkill("java");
        skillService.addSkill(skill);
        assertThat(skillService.findSkillByName("java"));
    }
@Test
    void updateSkill() throws SkillNotFoundException,SkillNotValidFields{
        Skill skill=new Skill();
        skill.setNameOfSkill("web");
        skillService.addSkill(skill);
    int skillId = 1;
    skillService.updateSkill(skill, skillId);
    assertThat(skillService.updateSkill(skill,1 ));
}


}

    //TODO update test


