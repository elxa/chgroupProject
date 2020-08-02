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

    /**
     * Get a list of skills
     *
     * @param skillName
     * @return
     * @throws SkillNotFoundException
     */
    @GetMapping("skill")
    public List<Skill> getListOfSkills(@RequestParam(required = false) String skillName) throws SkillNotFoundException {
        logger.info("Get a list of skills");
        return skillService.getSkills(skillName); //ean o xrhsths dwsei kapoia parametro tote 8a emfanisei mono ta skills me bash to onoma pou exei dwsei alliws 8a emfanisei olh th lista me
    }

    /**
     * Add skill in db
     *
     * @param skill
     * @return
     * @throws SkillNotFoundException
     * @throws SkillNotValidFields
     */
    @PostMapping("skill")
    public Skill addSkill(@RequestBody Skill skill) throws SkillNotFoundException, SkillNotValidFields {
        logger.info("Add skill in db");
        return skillService.addSkill(skill);
    }

    /**
     * Get a skill by id"
     *
     * @param skillId
     * @return
     * @throws SkillNotFoundException
     */
    @GetMapping("skill/{skillId}")
    public Skill getSkillById(@PathVariable int skillId) throws SkillNotFoundException {
        logger.info("Get a skill by id");
        return skillService.getSkillById(skillId);//epistrefei ena json
    }

    /**
     * Update a skill
     *
     * @param skill
     * @param skillId
     * @return
     * @throws SkillNotFoundException
     */
    @PutMapping("skill/{skillId}")
    public Skill updateSkillById(@RequestBody Skill skill, @PathVariable int skillId) throws SkillNotFoundException {
        logger.info("Update a skill");
        return skillService.updateSkill(skill, skillId);
    }

    /**
     * Delete a skill by Id
     *
     * @param skillId
     * @return
     */
    @DeleteMapping("skill/{skillId}")
    public boolean deleteSkillById(@PathVariable int skillId) throws SkillNotFoundException {
        logger.info("Delete a skill by Id");
        return skillService.deleteSkill(skillId);
    }

}
