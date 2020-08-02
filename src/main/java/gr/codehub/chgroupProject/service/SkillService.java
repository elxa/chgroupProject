package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;

import java.util.List;

public interface SkillService {
    public List<Skill> getSkills(String skillName) throws SkillNotFoundException;
    Skill addSkill(Skill skill) throws SkillNotFoundException, SkillNotValidFields; //throws CustomerCreationException; //gia na epibebaiwsoume oti autos o customer mphke
    List<Skill> getAllSkills();
    Skill updateSkill(Skill skill, int skillId) throws SkillNotFoundException;
    // throws CustomerNotFoundException;
    boolean deleteSkill(int skillId) throws SkillNotFoundException;
    Skill getSkillById(int skillId) throws SkillNotFoundException;
    Skill findSkillByName(String skillName) throws SkillNotFoundException;
}
