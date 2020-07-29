package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.JobOfferApplicant;
import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.*;
import gr.codehub.chgroupProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//select *
//        from ApplicantSkill askill, Skill s, JobOfferSkill js, Applicant a, JobOffer j
//        where askill.skill_id = s.id
//        and s.id = js.skill_id
//        and a.id = askill.applicant_id
//        and js.jobOffer_id = j.id
//        and j.level = a.level;


//select  a.id
//        --askill.skill_id ,  a.firstName   Applicant , j.id as JobOffer
//
//        from  Applicant a
//        inner join ApplicantSkill askill
//        on  a.id = askill.applicant_id
//        inner join skill s
//        on askill.skill_id = s.id
//        inner join JobOfferSkill js
//        on js.skill_id = askill.skill_id
//        inner join JobOffer j
//        on js.JobOffer_id = j.id
//        where j.id=1
//        group by a.id
//        having count(a.id) = (
//
//        select count (*)
//        from JobOfferSkill js
//
//        inner join JobOffer j
//        on js.JobOffer_id = j.id
//        where j.id=1
//        )


@Service
public class CreateAndMatchServiceImpl implements CreateAndMatchService {
    @Autowired
    CreateAndMatchRepository createAndMatchRepo;
    @Autowired
    ApplicantSkillRepository applicantSkillRepo;
    @Autowired
    JobOfferSkillRepository jobOfferSkillRepo;
    @Autowired
    ApplicantRepository applicantRepo;
    @Autowired
    JobOfferRepository jobOfferRepo;
    @Autowired
    JobOfferService jobOfferService;
    @Autowired
    ApplicantService applicantService;

    @Override
    public List<CreateAndMatch> getCreateAndMatchs() {
        return createAndMatchRepo.findAll();
    }

    @Override
    public List<CreateAndMatch> autoCreateAndMatch() {

        return null;
    }

    @Override
    public boolean autoCreateMatch() throws JobOfferNotFoundException {
        List<ApplicantSkill> applicantSkills = applicantSkillRepo.findAll();
        List<JobOfferSkill> jobOfferSkills = jobOfferSkillRepo.findAll();

        List<JobOfferApplicant> jobOfferApplicants = new ArrayList<>();

        for (int i = 0; i < jobOfferSkills.size(); i++) {
            for (int j = 0; j < applicantSkills.size(); j++) {
                if (jobOfferSkills.get(i).getSkill().getId() == applicantSkills.get(j).getSkill().getId()) {
                    System.out.println("joboffer ,applicant " + jobOfferSkills.get(i).getJobOffer().getId() + " , " + applicantSkills.get(j).getApplicant().getId() + " -> " + applicantSkills.get(j).getSkill().getId());

                    JobOffer jobOffer = jobOfferService.getJobOfferById(jobOfferSkills.get(i).getJobOffer().getId());
                    Applicant applicant = applicantService.getApplicantById(applicantSkills.get(j).getApplicant().getId());

                    //dhmiourgia enos antikeimenou tupou dto
                    JobOfferApplicant jobOfferApplicant = new JobOfferApplicant();
                    jobOfferApplicant.setJobOffer(jobOffer);
                    jobOfferApplicant.setApplicant(applicant);

                    jobOfferApplicants.add(jobOfferApplicant);
                }
            }
        }

        List<JobOfferApplicant> newJobOfferApplicant = new ArrayList<>();
        for (int i = 0; i < jobOfferApplicants.size(); i++) {
            int count = 0;

            if(!newJobOfferApplicant.contains(jobOfferApplicants.get(i))){
                System.out.println("ddhdhdj");
                newJobOfferApplicant.add(jobOfferApplicants.get(i));

            }


        }
        for (int i = 0; i < newJobOfferApplicant.size(); i++){
            System.out.println(newJobOfferApplicant.get(i).getJobOffer().getId()+""+newJobOfferApplicant.get(i).getApplicant().getId());
        }


        return true;
    }


//    @Override
//    public boolean autoCreateMatch() {
//        List<ApplicantSkill> applicantSkills = applicantSkillRepo.findAll();
//
//        List<JobOfferSkill> jobOfferSkills = jobOfferSkillRepo.findAll();
//        List<Skill> skills = skillRepo.findAll();
//
//
//        for (int i = 0; i < jobOfferSkills.size(); i++) {
//            for (int k = 0; k < skills.size(); k++) {
//                                    if (jobOfferSkills.get(i).getSkill().getId() == skills.get(k).getId()) {
//                                        System.out.println(jobOfferSkills.get(i).getSkill().getId());
//                                    }
//            }
//                for (int j = 0; j < applicantSkills.size(); j++) {
//
//
////                    if (jobOfferSkills.get(i).getSkill().getId() == skills.get(k).getId()
////                            && applicantSkills.get(j).getSkill().getId() == skills.get(k).getId()) {
//                        if (
//                                applicantSkills.get(j).getSkill().getId() == jobOfferSkills.get(i).getSkill().getId()) {
//                            System.out.println("****joboffer id****" + jobOfferSkills.get(i).getJobOffer().getId()
//                                    +  "****applicant id****" + applicantSkills.get(j).getApplicant().getId()
//                                    +"****joboffer skill****" + jobOfferSkills.get(i).getSkill().getId() );
////                            System.out.println("****joboffer skill****" + jobOfferSkills.get(i).getSkill().getId() + "****joboffer id****" + jobOfferSkills.get(i).getJobOffer().getId()
////                                    + "****applicant skill****" + applicantSkills.get(j).getSkill().getId()+ "****applicant id****" + applicantSkills.get(j).getApplicant().getId());
//                      //  System.out.println("****joboffer****" + jobOfferSkills.get(i).getSkill().getId() + "****applicant****" + applicantSkills.get(j).getSkill().getId() + "***** skill ***********" + skills.get(k).getId());
//
//
//                    }
//
//        //        }
//
//            }
//        }
//
//
//        return true;
//    }


}
