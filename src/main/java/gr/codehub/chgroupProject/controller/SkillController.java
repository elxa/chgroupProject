package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    //auto douleuei
    @GetMapping("skill")
    public List<Skill> getListOfSkills(@RequestParam(required = false) String skillName) throws SkillNotFoundException {
        return skillService.getSkills(skillName); //ean o xrhsths dwsei kapoia parametro tote 8a emfanisei mono ta skills me bash to onoma pou exei dwsei alliws 8a emfanisei olh th lista me
    }

    @PostMapping("skill")
    public Skill addSkill(@RequestBody Skill skill) throws SkillNotFoundException, SkillNotValidFields {
        return skillService.addSkill(skill);
    }

    @GetMapping("skill/{skillId}")
    public Skill getSkillById(@PathVariable int skillId) throws SkillNotFoundException {
        {
            return skillService.getSkillById(skillId);//epistrefei ena json
        }
    }

    @PutMapping("skill/{skillId}")
    public Skill updateSkillById(@RequestBody Skill skill, @PathVariable int skillId) throws SkillNotFoundException {
        return skillService.updateSkill(skill, skillId);
    }

    @DeleteMapping("skill/{skillId}")
    public boolean deleteSkillById(@PathVariable int skillId) {
        return skillService.deleteSkill(skillId);
    }

}


