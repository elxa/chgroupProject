package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;



    @PostMapping("skill")
    public Skill addSkill(@RequestBody Skill skill)
    //todo   throws CustomerCreationException
    {
        return skillService.addSkill(skill);
    }

    @GetMapping("skill/{skillId}")
    public Skill getSkillById(@PathVariable int skillId) //todo throws CustomerNotFoundException {
    {
        return skillService.getSkillById(skillId);//epistrefei ena json
    }

    @PutMapping("skill/{skillId}")
    public Skill updateSkillById(@RequestBody Skill skill, @PathVariable int skillId){
        return skillService.updateSkill(skill,skillId);
    }

    @DeleteMapping("skill/{skillId}")
    public boolean  deleteSkillById(@PathVariable int skillId){
        return skillService.deleteSkill(skillId);
    }

    //lista apo skill

    @GetMapping("skills")
    public List<Skill> getListOfSkills() {
        return skillService.getSkills();
    }
    //todo na ftia3oume to monopati giati to mperdeuei me to apo panw k xtupaei error
    @GetMapping("skill")
//    public Skill getSkillByName(@PathVariable("skillName") String skillName) throws SkillNotFoundException {
        public Skill getSkillByName(@RequestParam(required = false) String skillName) throws SkillNotFoundException {
        return skillService.findSkillByName(skillName);
    }

}


