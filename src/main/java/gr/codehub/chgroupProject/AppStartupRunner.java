package gr.codehub.chgroupProject;

import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.util.IO;
import gr.codehub.chgroupProject.util.ReadApplicants;
import gr.codehub.chgroupProject.util.ReadJobOffers;
import gr.codehub.chgroupProject.util.ReadSkills;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
    public class AppStartupRunner implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {


            Workbook workbook = IO.createWorkbook("data for rs-api.xlsx");
//
//            ReadSkills rs = new ReadSkills();
//            List<Skill> skills= rs.ReadSkillsFromExcel(workbook);
//            System.out.println(skills);
//
//            ReadApplicants ra =  new ReadApplicants();
         //   List <SkillsDto> applicantsDto = ra.readApplicantsFromExcel(workbook);
         //   System.out.println(applicantsDto);
//
//            List<Applicant> applicantsDb = new ArrayList<>();
//
//            for(int j=0; j<=applicantsDto.size(); j++) {
//                Applicant applicant = (Applicant) applicantsDto.get(j).getO();
//                List<String> applicant_skills = applicantsDto.get(j).getSkills();
//
//                List<ApplicantSkill> applicantSkillList = new ArrayList<>();
//
//                for (int i = 0; i <= applicant_skills.size(); i++) {
//                    Skill skill = SkillRepository.findByName();
//                    ApplicantSkill applicantSkill = new ApplicantSkill();
//                    applicantSkill.setApplicant(applicant);
//                    applicantSkill.setSkill(skill);
//                    applicantSkillList.add(applicantSkill);
//                }
//                applicant.setApplicantSkills(applicantSkillList);
//                applicantsDb.add(applicant);
//            }

            //ReadJobOffers rjo = new ReadJobOffers();
            //System.out.println(rjo.ReadJobOffersFromExcel(workbook));

            workbook.close();
        }
    }



