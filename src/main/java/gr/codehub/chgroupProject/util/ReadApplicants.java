package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.SkillService;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@NoArgsConstructor

@Service
public class ReadApplicants {

    private ApplicantService applicantService;
    private SkillService skillService;
    private ApplicantSkillService applicantSkillService;

    @Autowired
    public ReadApplicants(ApplicantService applicantService, SkillService skillService, ApplicantSkillService applicantSkillService){
        this.applicantService = applicantService;
        this.skillService = skillService;
        this.applicantSkillService = applicantSkillService;
    }

    public List<Applicant> readApplicantsFromExcel(Workbook workbook) throws IOException, SkillNotFoundException {

        //List<ApplicantSkill> applicantSkillList = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);

        List<Applicant> applicants = new ArrayList<>();
        boolean firstTime = true;

        for (Row row : sheet) {
            if (firstTime) {
                firstTime = false;
                continue;
            }
            Applicant a = new Applicant();
            a.setFirstName(row.getCell(0).getStringCellValue());
            a.setLastName(row.getCell(1).getStringCellValue());
            a.setAddress(row.getCell(2).getStringCellValue());
            a.setRegion(row.getCell(3).getStringCellValue());
            a.setEducation(row.getCell(4).getStringCellValue());
            a.setLevel(row.getCell(5).getStringCellValue());
            a.setAvailable(true);

            //save a stous applicants sth vasi
            applicants.add(a); // den xreaizetai
            applicantService.addApplicant(a);

            int skillsCountCell = 6;
            //List<String> skills = new ArrayList<>();

//            while (row.getCell(skillsCountCell) != null) {
//
//                String skillName = row.getCell(skillsCountCell).getStringCellValue();
//
//                ApplicantSkill applicantSkill = new ApplicantSkill();
//                applicantSkill.setApplicant(a);
//                Skill skill = skillService.findSkillByName(skillName);
//                applicantSkill.setSkill(skill);
//                // applicantSkillList.add(applicantSkill); xreiazetai?? thelei sth vasi
//                applicantSkillService.addApplicantSkill(a.getId(),skill.getId());
//
//                skillsCountCell++;
//            }



        }


        // Closing the workbook
        //workbook.close(); to kleinw ston appstartup

        return applicants;
    }
}