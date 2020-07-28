package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ReadApplicants {
    public List<Applicant> readApplicantsFromExcel(Workbook workbook) throws IOException {

        List<ApplicantSkill> applicantSkillList = new ArrayList<>();

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

            applicants.add(a);
            //save a stous applicants sth vasi
            int skillsCountCell = 6;

            List<String> skills = new ArrayList<>();

            while (row.getCell(skillsCountCell) != null) {

                String skill = row.getCell(skillsCountCell).getStringCellValue();

                ApplicantSkill applicantSkill = new ApplicantSkill();
                applicantSkill.setApplicant(a);
                //Skill skill = SkillRepository.findByName(skill);
                //applicantSkill.setSkill(skill);
               // applicantSkillList.add(applicantSkill); xreiazetai?? thelei sth vasi
                skillsCountCell++;
            }

        }


        // Closing the workbook
        //workbook.close(); to kleinw ston appstartup

        return applicants;
    }
}