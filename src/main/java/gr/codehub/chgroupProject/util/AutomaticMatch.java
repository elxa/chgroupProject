package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.CreateAndMatchService;
import gr.codehub.chgroupProject.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutomaticMatch {

    private ApplicantService applicantService;
    private JobOfferService jobOfferService;
    private CreateAndMatchService createAndMatchService;

    @Autowired
    public AutomaticMatch(ApplicantService applicantService, JobOfferService jobOfferService,
                          CreateAndMatchService createAndMatchService) {
        this.applicantService = applicantService;
        this.jobOfferService = jobOfferService;
        this.createAndMatchService = createAndMatchService;
    }

    public List<CreateAndMatch> DoAutomaticMatch() throws ApplicantNotFoundException, JobOfferNotFoundException, SkillNotFoundException {

        List<Applicant> applicantList = applicantService.getApplicants();
        List<JobOffer> jobOfferList = jobOfferService.getJobOffers();


        List<List<Integer>> applicantSkillsIdList = new ArrayList<>(); //NEW

        for (int i = 0; i < applicantList.size(); i++) {
            List<Integer> skillIdListApp = new ArrayList<>(); //NEW
            applicantSkillsIdList.add(skillIdListApp);  // NEW

            Applicant applicant = applicantList.get(i);
            for (int j = 0; j < applicant.getApplicantSkills().size(); j++) {
                //Skill skill = new Skill();
                Skill skill = applicant.getApplicantSkills().get(j).getSkill();
                skillIdListApp.add(skill.getId()); //NEW
            }
        }

        List<List<Integer>> jobOfferSkillsIdList = new ArrayList<>(); //NEW

        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillIdListJob = new ArrayList<>(); //NEW
            jobOfferSkillsIdList.add(skillIdListJob);  // NEW

            JobOffer joboffer = jobOfferList.get(i);
            for (int j = 0; j < joboffer.getJobOfferSkill().size(); j++) {
                // Skill skill = new Skill();
                Skill skill = joboffer.getJobOfferSkill().get(j).getSkill();
                skillIdListJob.add(skill.getId()); //NEW
            }
        }

        // System.out.println(jobOfferSkillsIdList);
        //return applicantSkillsIdList;


        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillsIdJob = jobOfferSkillsIdList.get(i);

            if (!jobOfferList.get(i).getAvailable()) continue;

            for (int j = 0; j < applicantList.size(); j++) {
                List<Integer> skillsIdApp = applicantSkillsIdList.get(j);

                if (!applicantList.get(j).getAvailable()) continue;
                if (!jobOfferList.get(i).getLevel().equals(applicantList.get(j).getLevel())) continue;

                if (skillsIdApp.containsAll(skillsIdJob)) {
                    System.out.println("***MATCH***" + jobOfferList.get(i) + "WITH APPLICANT" + applicantList.get(j));


                    //if(!createAndMatchService.checkIfApplicantIdAndJobOfferIdExist(applicantList.get(j).getId(), jobOfferList.get(i).getId())){
                        createAndMatchService.addCreateAndMatch(applicantList.get(j).getId(), jobOfferList.get(i).getId());
                    //}
                }
            }
        }
        return createAndMatchService.getCreateAndMatches();


    }
}
