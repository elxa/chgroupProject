package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.ApplicantSkillRepository;
import gr.codehub.chgroupProject.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//todo na paroume mia lista me ola ta applicantSkill



@Service
public class ApplicantSkillServiceImpl implements ApplicantSkillService {

    @Autowired
    private ApplicantSkillRepository applicantSkillRepo;
    @Autowired
    private ApplicantRepository applicantRepo;
    @Autowired
    public SkillRepository skillRepo;

    /**
     * We create a method in order to add a new applicant skill
     * @param applicantId
     * @param skillId
     * @return a
     * @throws SkillNotFoundException
     * @throws ApplicantNotFoundException
     */


    @Override
    public ApplicantSkill addApplicantSkill(int applicantId, int skillId) throws SkillNotFoundException, ApplicantNotFoundException
    {
        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        ()-> new ApplicantNotFoundException("Applicant Not Found "));

        Skill skillInDb = skillRepo.findById(skillId)
                .orElseThrow(
                       () -> new SkillNotFoundException("Skill not found")
               );

        ApplicantSkill applicantSkill= new ApplicantSkill();
        applicantSkill.setApplicant(applicantInDb);
        applicantSkill.setSkill(skillInDb);

        applicantSkillRepo.save(applicantSkill);
        return applicantSkill;

    }
}
