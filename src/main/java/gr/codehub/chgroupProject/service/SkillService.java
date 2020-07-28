package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    public List<Skill> getSkills(String skillName) throws SkillNotFoundException;
    Skill addSkill(Skill skill) throws SkillNotFoundException, SkillNotValidFields; //throws CustomerCreationException; //gia na epibebaiwsoume oti autos o customer mphke
    Skill updateSkill(Skill skill, int skillId) throws SkillNotFoundException;
    // throws CustomerNotFoundException;
    boolean deleteSkill(int skillId);
    Skill getSkillById(int skillId) throws SkillNotFoundException;
    Skill findSkillByName(String skillName) throws SkillNotFoundException;
}
