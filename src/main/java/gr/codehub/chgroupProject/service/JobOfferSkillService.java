package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.JobOfferRequiredSkillDTO;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.JobOfferSkill;

import java.util.List;

public interface JobOfferSkillService {
    JobOfferSkill addJobOfferSkill(int jobOfferId, int skillId) throws SkillNotFoundException, JobOfferNotFoundException;
    List<JobOfferRequiredSkillDTO> theMostOfferedSkillsInJobOffers();

}
