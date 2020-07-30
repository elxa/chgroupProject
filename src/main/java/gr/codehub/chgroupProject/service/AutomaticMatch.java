package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.controller.Reporter;
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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AutomaticMatch {

    Logger logger = LoggerFactory.getLogger(AutomaticMatch.class);

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

        logger.info("Return a list with automatic match");

        List<Applicant> applicantList = applicantService.getApplicants();  //pairnei mia lista me tous applicants apo th bash
        List<JobOffer> jobOfferList = jobOfferService.getJobOffers(); //pairnei mia lista me ta job Offer apo th bash

        List<List<Integer>> applicantSkillsIdList = new ArrayList<>(); //NEW   //dhmiourgw muia lista opou periexei ta ids twn skills tou applicant

        for (int i = 0; i < applicantList.size(); i++) {
            List<Integer> skillIdListApp = new ArrayList<>(); //NEW  //dhmiourgw mia lista apo skills
            applicantSkillsIdList.add(skillIdListApp);  // NEW

            Applicant applicant = applicantList.get(i);
            for (int j = 0; j < applicant.getApplicantSkills().size(); j++) {
                //Skill skill = new Skill();
                Skill skill = applicant.getApplicantSkills().get(j).getSkill();
                skillIdListApp.add(skill.getId()); //NEW
            }
        }

        List<List<Integer>> jobOfferSkillsIdList = new ArrayList<>(); //NEW  //kanw to idio k me to job offer

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

        for (int i = 0; i < jobOfferList.size(); i++) {
            List<Integer> skillsIdJob = jobOfferSkillsIdList.get(i);

            if (!jobOfferList.get(i).getAvailable()) continue;

            for (int j = 0; j < applicantList.size(); j++) {
                List<Integer> skillsIdApp = applicantSkillsIdList.get(j);

                if (!applicantList.get(j).getAvailable()) continue;
                if (!jobOfferList.get(i).getLevel().equals(applicantList.get(j).getLevel())) continue;

                if (skillsIdApp.containsAll(skillsIdJob)) {
                    System.out.println("***MATCH***" + jobOfferList.get(i) + "WITH APPLICANT" + applicantList.get(j));


                    if(!createAndMatchService.checkIfApplicantIdAndJobOfferIdExist(applicantList.get(j), jobOfferList.get(i))){  //gia na mhn 3anagemisei h bash me ta ids pou hdh uparxoun
                        createAndMatchService.addCreateAndMatch(applicantList.get(j).getId(), jobOfferList.get(i).getId());
                    }
                }
            }
        }
        return createAndMatchService.getCreateAndMatches();
    }
}
