package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.ApplicantSkill;

public interface ApplicantSkillService {

    ApplicantSkill addApplicantSkill(int applicantId, int skillId) throws ApplicantNotFoundException, SkillNotFoundException;
}
