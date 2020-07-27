package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("skill")
    public List<Skill> getListOfSkills() {
        return skillService.getSkills();
    }

    @PostMapping("skill")
    public Skill addSkill(@RequestBody Skill skill)
    //todo   throws CustomerCreationException
    {
        return skillService.addSkill(skill);
    }

    @GetMapping("skill/{skillId}")
    public Skill getSkillById(@PathVariable int skillId) //todo throws CustomerNotFoundException {
    {
        return skillService.getSkill(skillId);//epistrefei ena json
    }

    @PutMapping("skill/{skillId}")
    public Skill updateSkillById(@RequestBody Skill skill, @PathVariable int skillId){
        return skillService.updateSkill(skill,skillId);
    }

    @DeleteMapping("skill/{skillId}")
    public boolean  deleteSkillById(@PathVariable int skillId){
        return skillService.deleteSkill(skillId);
    }


}


