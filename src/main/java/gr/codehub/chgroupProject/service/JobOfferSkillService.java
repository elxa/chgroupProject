package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.ApplicantSkillRepository;
import gr.codehub.chgroupProject.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface JobOfferSkillService {
    JobOfferSkill addJobOfferSkill(int jobOfferId, int skillId) throws SkillNotFoundException, JobOfferNotFoundException;

}
