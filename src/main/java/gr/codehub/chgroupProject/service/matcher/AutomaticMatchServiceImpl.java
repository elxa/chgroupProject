package gr.codehub.chgroupProject.service.matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.CreateAndMatchRepository;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.JobOfferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AutomaticMatchServiceImpl implements AutomaticMatchService {
    Logger logger = LoggerFactory.getLogger(AutomaticMatchServiceImpl.class);

    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private ManualMatchService createManualMatchService;
    @Autowired
    private ApplicantRepository applicantRepo;
    @Autowired
    private JobOfferRepository jobOfferRepo;
    @Autowired
    private CreateAndMatchRepository createAndMatchRepo;

    /**
     * Create a Applicant Skills Id List
     *
     * @param applicantList
     * @return Return a list of applicant skills id
     */
    private List<List<Integer>> createApplicantSkillsIdList(List<Applicant> applicantList) {
        logger.info("Return a list of applicant skills id");

        List<List<Integer>> applicantSkillsIdList = new ArrayList<>();
        for (int i = 0; i < applicantList.size(); i++) {
            List<Integer> skillIdListApp = new ArrayList<>();
            applicantSkillsIdList.add(skillIdListApp);
            Applicant applicant = applicantList.get(i);
            for (int j = 0; j < applicant.getApplicantSkills().size(); j++) {
                Skill skill = applicant.getApplicantSkills().get(j).getSkill();
                skillIdListApp.add(skill.getId());
            }
        }
        return applicantSkillsIdList;
    }

    /**
     * Return a list of Job Offer skills id
     *
     * @param jobOfferList
     * @return Return a list of Job Offer skills id
     */
    private List<List<Integer>> createJobOfferSkillsIdList(List<JobOffer> jobOfferList) {
        logger.info("Return a list of Job Offer skills id");

        List<List<Integer>> jobOfferSkillsIdList = new ArrayList<>();
        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillIdListJob = new ArrayList<>();
            jobOfferSkillsIdList.add(skillIdListJob);
            JobOffer joboffer = jobOfferList.get(i);
            for (int j = 0; j < joboffer.getJobOfferSkill().size(); j++) {
                Skill skill = joboffer.getJobOfferSkill().get(j).getSkill();
                skillIdListJob.add(skill.getId());
            }
        }
        return jobOfferSkillsIdList;
    }

    //todo na to 3anadw
    @Override
    public List<CreateAndMatch> doAutomaticMatch(boolean partial) throws ApplicantNotFoundException, JobOfferNotFoundException, SkillNotFoundException {

        logger.info("Do automatic match which check if applicant list ids include in job offer skills id ");

        List<Applicant> applicantList = applicantService.getListApplicants();
        List<JobOffer> jobOfferList = jobOfferService.getJobOfferList();

        List<List<Integer>> applicantSkillsIdList = createApplicantSkillsIdList(applicantList);
        List<List<Integer>> jobOfferSkillsIdList = createJobOfferSkillsIdList(jobOfferList);
        List<CreateAndMatch> currentMatches = new ArrayList<>();
        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillsIdJob = jobOfferSkillsIdList.get(i);
            if (!jobOfferList.get(i).getAvailable()) continue;
            for (int j = 0; j < applicantList.size(); j++) {
                List<Integer> skillsIdApp = applicantSkillsIdList.get(j);
                if (!applicantList.get(j).getAvailable()) continue;
                if (!jobOfferList.get(i).getLevel().equals(applicantList.get(j).getLevel())) continue;
                if (skillsIdApp.containsAll(skillsIdJob)) {
                    CreateAndMatch createAndMatch = createManualMatchService
                               .addCreateAndMatch(applicantList.get(j).getId(), jobOfferList.get(i).getId());
                    currentMatches.add(createAndMatch);
                } else {
                    if (partial == true) {
                        if (CollectionUtils.containsAny(skillsIdApp, skillsIdJob)) {
                            CreateAndMatch createAndMatch = createManualMatchService
                                      .addCreateAndMatch(applicantList.get(j).getId(), jobOfferList.get(i).getId());
                            currentMatches.add(createAndMatch);
                        }
                    }
                }
            }
        }
        return currentMatches;
    }

}