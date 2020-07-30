package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.ApplicantSkill;

public interface ApplicantSkillService {

    ApplicantSkill addApplicantSkill(int applicantId, int skillId)
            throws ApplicantNotFoundException, SkillNotFoundException;
}
