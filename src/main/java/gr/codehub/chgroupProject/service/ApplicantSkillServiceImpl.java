package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.controller.Reporter;
import gr.codehub.chgroupProject.dto.ApplicantOfferedSkillDTO;
import gr.codehub.chgroupProject.dto.skillsDontMatchToApplicantsDTO;
import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.ApplicantSkillRepository;
import gr.codehub.chgroupProject.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApplicantSkillServiceImpl implements ApplicantSkillService {

    Logger logger = LoggerFactory.getLogger(Reporter.class);

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
        logger.info("Make match an applicant in skill");
        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        ()-> new ApplicantNotFoundException("Applicant Not Found "));

        Skill skillInDb = skillRepo.findById(skillId)
                .orElseThrow(
                        () -> new SkillNotFoundException("Skill not found")
                );

        ApplicantSkill applicantSkill = new ApplicantSkill();
        applicantSkill.setApplicant(applicantInDb);
        applicantSkill.setSkill(skillInDb);

        applicantSkillRepo.save(applicantSkill);
        return applicantSkill;
    }

    /**
     * The Most Offered Skills in other words the skills which exist in applicants
     *
     * @return a list of skills which applicant have
     */
    @Override
    public List<ApplicantOfferedSkillDTO> theMostOfferedSkills() {
        logger.info("The most offered skills");
        return applicantRepo.howManyTimesSkillAppearsInApplicantSkills();
    }

    /**
     * A list of skills Which job offer need and applicants dont have DontMatchesToApplicants
     * @return a list of skills which dont matches to applicants
     */
    @Override
    public List<skillsDontMatchToApplicantsDTO> skillsWhichDontMatchesToApplicants() {
        logger.info("The skills that required in jobs and dont match with applicants");
        return applicantSkillRepo.skillsWhichDontMatchesToApplicants();
    }


}
