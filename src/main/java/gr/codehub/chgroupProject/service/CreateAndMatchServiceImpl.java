package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ApplicantSkillRepository applicantSkillRepository;
    @Autowired
    JobOfferSkillRepository jobOfferSkillRepository;

    @Override
    public List<CreateAndMatch> getCreateAndMatchs() {
        return createAndMatchRepo.findAll();
    }

    @Override
    public List<CreateAndMatch> autoCreateAndMatch() {

        return null;
    }

//    @Override
//    public CreateAndMatch addCreateAndMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException {
//        Applicant applicantInDb = applicantRepo.findById(applicantId)
//                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not found"));
//
//        JobOffer jobOfferInDb = jobOfferRepo.findById(jobOfferId)
//                .orElseThrow(() -> new JobOfferNotFoundException("JobOffer Not found"));
//
//        CreateAndMatch createAndMatch = new CreateAndMatch();
//        createAndMatch.setApplicant(applicantInDb);
//        createAndMatch.setJobOffer(jobOfferInDb);
//        //todo swso format gia thn hmeromhnia
//
//
//        return createAndMatchRepo.save(createAndMatch);
//    }


}
