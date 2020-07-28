package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotValidFields;
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
    public Skill addSkill(@RequestBody Skill skill) throws SkillNotFoundException, SkillNotValidFields //todo   throws CustomerCreationException
    {
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
        return skillService.updateSkill(skill,skillId);
    }

    @DeleteMapping("skill/{skillId}")
    public boolean  deleteSkillById(@PathVariable int skillId){
        return skillService.deleteSkill(skillId);
    }

    //todo na ftia3oume to monopati giati to mperdeuei me to apo panw k xtupaei error
    @GetMapping("skill/skillByName")
    public Skill getSkillByName(@RequestParam String skillName) throws SkillNotFoundException {
        return skillService.findSkillByName(skillName);
    }


}


