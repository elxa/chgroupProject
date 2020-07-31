package gr.codehub.chgroupProject.service.Matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.JobOfferService;
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
    private ApplicantRepository applicantRepo;
    private JobOfferRepository jobOfferRepo;

    @Autowired
    public AutomaticMatchServiceImpl(ApplicantService applicantService, JobOfferService jobOfferService, CreateManualMatchService createManualMatchService, ApplicantRepository applicantRepo, JobOfferRepository jobOfferRepo) {
        this.applicantService = applicantService;
        this.jobOfferService = jobOfferService;
        this.createManualMatchService = createManualMatchService;
        this.applicantRepo = applicantRepo;
        this.jobOfferRepo = jobOfferRepo;
    }



    @Override
    public List<CreateAndMatch> DoAutomaticMatch(boolean partial)
            throws ApplicantNotFoundException, JobOfferNotFoundException, SkillNotFoundException {


        //  List<Applicant> applicantList = applicantService.getApplicants();  --->>>//TODO TO ALLA3A
        List<Applicant> applicantList = applicantRepo.findAll();
        // List<JobOffer> jobOfferList = jobOfferService.getJobOffers();
        List<JobOffer> jobOfferList = jobOfferRepo.findAll();


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

        List<List<Integer>> jobOfferSkillsIdList = new ArrayList<>();

        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillIdListJob = new ArrayList<>();
            jobOfferSkillsIdList.add(skillIdListJob);

            JobOffer joboffer = jobOfferList.get(i);
            for (int j = 0; j < joboffer.getJobOfferSkill().size(); j++) {
                // Skill skill = new Skill();
                Skill skill = joboffer.getJobOfferSkill().get(j).getSkill();
                skillIdListJob.add(skill.getId());
            }
        }

        List<CreateAndMatch> currentMatches = new ArrayList<>();

        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillsIdJob = jobOfferSkillsIdList.get(i);

            if (!jobOfferList.get(i).getAvailable()) continue;

            for (int j = 0; j < applicantList.size(); j++) {
                List<Integer> skillsIdApp = applicantSkillsIdList.get(j);

                if (!applicantList.get(j).getAvailable()) continue;
                if (!jobOfferList.get(i).getLevel().equals(applicantList.get(j).getLevel())) continue;

                if (skillsIdApp.containsAll(skillsIdJob)) {
                    System.out.println("***MATCH***" + jobOfferList.get(i) + "WITH APPLICANT" + applicantList.get(j));

                    //   if(!createAndMatchService.checkIfApplicantIdAndJobOfferIdExist(applicantList.get(j), jobOfferList.get(i))){
                    CreateAndMatch createAndMatch = createManualMatchService.addCreateAndMatch(applicantList.get(j).getId(), jobOfferList.get(i).getId());
                    currentMatches.add(createAndMatch);
                    //    }
                }
                else {
                    if (partial == true) {
                        if (CollectionUtils.containsAny(skillsIdApp, skillsIdJob)) {

                            System.out.println("***MATCH***" + jobOfferList.get(i) + "WITH APPLICANT" + applicantList.get(j));
                            CreateAndMatch createAndMatch = createManualMatchService.addCreateAndMatch(applicantList.get(j).getId(), jobOfferList.get(i).getId());
                            currentMatches.add(createAndMatch);
                        }

                    }
                }
            }
        }
        // return createAndMatchService.getCreateAndMatches();

        return currentMatches;


    }
}
