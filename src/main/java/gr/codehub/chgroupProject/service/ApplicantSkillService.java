package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.ApplicantSkillDTO;
import gr.codehub.chgroupProject.dto.skillsDontMatchToApplicantsDTO;
import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;

import java.util.List;

public interface ApplicantSkillService {

    ApplicantSkill addApplicantSkill(int applicantId, int skillId) throws ApplicantNotFoundException, SkillNotFoundException;
    List<ApplicantSkillDTO> theMostOfferedSkills();
    List<skillsDontMatchToApplicantsDTO> skillsWhichDontMatchesToApplicants();

}
