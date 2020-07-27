package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> getSkills();
    Skill addSkill(Skill skill); //throws CustomerCreationException; //gia na epibebaiwsoume oti autos o customer mphke
    Skill updateSkill(Skill skill, int skillId);
    // throws CustomerNotFoundException;
    boolean deleteSkill(int skillId);
    Skill getSkill(int skillId);
}
