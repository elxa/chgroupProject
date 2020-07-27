package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.ApplicantSkillRepository;
import gr.codehub.chgroupProject.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantSkillServiceImpl implements ApplicantSkillService {


    @Autowired
    private ApplicantSkillRepository applicantSkillRepo;
    @Autowired
    private ApplicantRepository applicantRepo;
    @Autowired
    public SkillRepo skillRepo;


    @Override
    public ApplicantSkill addApplicantSkill(int applicantId, int skillId)
    //TODO throws ApplicantNotFoundException , SkillNotFoundException
    {
        Applicant applicantInDb = applicantRepo.findById(applicantId).get();
//        .orElseThrow(
//                      ()-> new ApplicantNotFound("No such applicant")
//                );

        Skill skillInDb = skillRepo.findById(skillId).get();
//                .orElseThrow(
//                        () -> new SkillNotFound("No suck skill")
//                );

        ApplicantSkill applicantSkill= new ApplicantSkill();
        applicantSkill.setApplicant(applicantInDb);
        applicantSkill.setSkill(skillInDb);

        applicantSkillRepo.save(applicantSkill);
        return applicantSkill;

    }
}
