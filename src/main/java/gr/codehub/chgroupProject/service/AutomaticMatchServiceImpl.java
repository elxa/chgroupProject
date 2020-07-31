package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutomaticMatchServiceImpl implements AutomaticMatchService {

    private ApplicantService applicantService;
    private JobOfferService jobOfferService;
    private CreateManualMatchService createManualMatchService;

    @Autowired
    public AutomaticMatchServiceImpl(ApplicantService applicantService, JobOfferService jobOfferService,
                                     CreateManualMatchService createManualMatchService) {
        this.applicantService = applicantService;
        this.jobOfferService = jobOfferService;
        this.createManualMatchService = createManualMatchService;
    }

    private List<List<Integer>> createApplicantSkillsIdList(List<Applicant> applicantList) {
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

    private List<List<Integer>> createJobOfferSkillsIdList(List<JobOffer> jobOfferList) {
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


    @Override
    public List<CreateAndMatch> DoAutomaticMatch(boolean partial)
            throws ApplicantNotFoundException, JobOfferNotFoundException, SkillNotFoundException {

        List<Applicant> applicantList = applicantService.getApplicants();
        List<JobOffer> jobOfferList = jobOfferService.getJobOffers();

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
