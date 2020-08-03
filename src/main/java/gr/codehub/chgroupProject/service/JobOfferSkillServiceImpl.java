package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.JobOfferRequiredSkillDTO;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import gr.codehub.chgroupProject.repository.JobOfferSkillRepository;
import gr.codehub.chgroupProject.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JobOfferSkillServiceImpl implements JobOfferSkillService {
    Logger logger = LoggerFactory.getLogger(JobOfferSkillServiceImpl.class);

    @Autowired
    JobOfferSkillRepository jobOfferSkillRepo;
    @Autowired
    SkillRepository skillRepo;
    @Autowired
    JobOfferRepository jobOfferRepo;

    /**
     * We use this method in order to be able to add a specific job offer skill
     * @param jobOfferId
     * @param skillId
     * @return jobOfferSkill
     * @throws SkillNotFoundException
     * @throws JobOfferNotFoundException
     */
    @Override
    public JobOfferSkill addJobOfferSkill(int jobOfferId, int skillId) throws SkillNotFoundException, JobOfferNotFoundException {
        logger.info("Add Job Offer Skill in db");
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId)
                        .orElseThrow(
                        () -> new JobOfferNotFoundException("Job offer not found")
                              );

        Skill skillInDb = skillRepo.findById(skillId)
                .orElseThrow(
                        () -> new SkillNotFoundException("Skill not found")
                );

        JobOfferSkill jobOfferSkill = new JobOfferSkill();
        jobOfferSkill.setJobOffer(jobOfferInDb);
        jobOfferSkill.setSkill(skillInDb);

        jobOfferSkillRepo.save(jobOfferSkill);
        return jobOfferSkill;
    }

    /**
     * a list of the most Offered Skills in Job Offers
     *
     * @return a list of the most Offered Skills in Job Offers
     */

    @Override
    public List<JobOfferRequiredSkillDTO> theMostOfferedSkillsInJobOffers() {
        logger.info("The Most Offered Skills In JobOffers");
        return jobOfferRepo.howManyTimesSkillAppearsInJobOfferSkills();
    }

}
