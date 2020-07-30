package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SkillController {
    Logger logger = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    //auto douleuei
    @GetMapping("skill")
    public List<Skill> getListOfSkills(
            @RequestParam(required = false) String skillName)
            throws SkillNotFoundException
    {
        logger.info("An INFO Message");
        return skillService.getSkills(skillName); //ean o xrhsths dwsei kapoia parametro tote 8a emfanisei mono ta skills me bash to onoma pou exei dwsei alliws 8a emfanisei olh th lista me
    }

    @PostMapping("skill")
    public Skill addSkill(@RequestBody Skill skill) throws SkillNotFoundException, SkillNotValidFields {
        logger.info("Add skill in db");
        return skillService.addSkill(skill);
    }

    @GetMapping("skill/{skillId}")
    public Skill getSkillById(@PathVariable int skillId) throws SkillNotFoundException {
        {
            logger.info("Get skill by id");
            return skillService.getSkillById(skillId);//epistrefei ena json
        }
    }

    @PutMapping("skill/{skillId}")
    public Skill updateSkillById(@RequestBody Skill skill, @PathVariable int skillId) throws SkillNotFoundException {
        logger.info("Update a skill");
        return skillService.updateSkill(skill, skillId);
    }

    @DeleteMapping("skill/{skillId}")
    public boolean deleteSkillById(@PathVariable int skillId) {
        logger.info("Delete a skill by Id");
        return skillService.deleteSkill(skillId);
    }

}


