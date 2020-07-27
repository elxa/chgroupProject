package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class JobOfferSkillServiceImpl implements JobOfferSkillService {
    @Autowired
    JobOfferSkillRepository jobOfferSkillRepo;
    @Autowired
    SkillRepository skillRepo;
    @Autowired
    JobOfferRepository jobOfferRepo;


    @Override
    public JobOfferSkill addJobOfferSkill(int jobOfferId, int skillId) {
        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId).get();
        //                .orElseThrow("sss"
//                     //   () -> new CustomerNotFoundException("not such customer")
//                              );

        Skill skillInDb = skillRepo.findById(skillId).get();
        //                .orElseThrow("sss"
//                     //   () -> new CustomerNotFoundException("not such customer")
//                              );

        JobOfferSkill jobOfferSkill = new JobOfferSkill();
        jobOfferSkill.setJobOffer(jobOfferInDb);
        jobOfferSkill.setSkill(skillInDb);

        jobOfferSkillRepo.save(jobOfferSkill);
        return jobOfferSkill;


    }
}
