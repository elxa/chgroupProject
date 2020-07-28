package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SkillServiceImplTest {

    @Autowired
    SkillService ss;

    @Test
    void getSkills() throws SkillNotFoundException, SkillNotValidFields{
        Skill ss1=new Skill();
        ss1.setNameOfSkill("Databases");
        ss1.setId(1);

        ss.addSkill(ss1);
        ss.addSkill(ss1);

        assertEquals(2, ss.getSkills().size());
    }

    @Test
    void addSkill() throws SkillNotValidFields, SkillNotFoundException{
        List<Skill> skills=new ArrayList<>();
        Skill ss1=new Skill();
        ss1.setNameOfSkill("Databases");
        ss1.setId(1);

        Skill ss2=new Skill();
        ss2.setNameOfSkill("Scrum");
        ss2.setId(2);

        skills.add(ss.addSkill(ss1));
        skills.add(ss.addSkill(ss2));

        assertEquals(2, skills.size());
    }

    @Test
    void getSkill() throws SkillNotFoundException,SkillNotValidFields{
        Skill ss1=new Skill();
        ss1.setNameOfSkill("Databases");
        ss1.setId(1);

        ss.addSkill(ss1);
        assertEquals(1, ss.getSkillById((1)));
    }

    @Test
    void deleteSkill() throws SkillNotFoundException, SkillNotValidFields {
        Skill ss1 = new Skill();
        ss1.setNameOfSkill("Angular");
        ss1.setId(1);

        ss.addSkill(ss1);

        assertEquals(true , ss.deleteSkill((1)));
    }


    @Test
    void findSkillByName() throws SkillNotFoundException, SkillNotValidFields {
        Skill ss1=new Skill();
        ss1.setNameOfSkill("Databases");

        ss.addSkill(ss1);

        assertEquals( "Databases", ss.findSkillByName(toString()));

    }

//TODO update test

}
