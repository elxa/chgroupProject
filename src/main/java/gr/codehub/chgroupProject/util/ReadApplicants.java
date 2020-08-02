package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.ApplicantSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.ApplicantService;
import gr.codehub.chgroupProject.service.ApplicantSkillService;
import gr.codehub.chgroupProject.service.SkillService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@Service
@Slf4j
public class ReadApplicants {

    Logger logger = LoggerFactory.getLogger(ReadApplicants.class);

    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private ApplicantSkillService applicantSkillService;

    /**
     * Read applicants from excel and them in db
     *
     * @param workbook
     * @return
     * @throws IOException
     * @throws ApplicantNotValidFields
     * @throws ApplicantNotFoundException
     * @throws SkillNotFoundException
     * @throws SkillNotValidFields
     */
    public List<Applicant> readApplicantsFromExcel(Workbook workbook) throws IOException, ApplicantNotValidFields, ApplicantNotFoundException, SkillNotFoundException, SkillNotValidFields {
        logger.info("read applicants from an exchel file and add in db");
        Sheet sheet = workbook.getSheetAt(0);

        List<Applicant> applicants = new ArrayList<>();
        boolean firstTime = true;

        List<List<Integer>> applicantSkillsIdList = new ArrayList<>(); //NEW

        for (Row row : sheet) {
            if (firstTime) {
                firstTime = false;
                continue;
            }

            List<ApplicantSkill> applicantSkillList = new ArrayList<>();

            Applicant a = new Applicant();
            a.setFirstName(row.getCell(0).getStringCellValue());
            a.setLastName(row.getCell(1).getStringCellValue());
            a.setAddress(row.getCell(2).getStringCellValue());
            a.setRegion(row.getCell(3).getStringCellValue());
            a.setEducation(row.getCell(4).getStringCellValue());
            a.setLevel(row.getCell(5).getStringCellValue());
            a.setAvailable(true);


            applicants.add(a);
            applicantService.addApplicant(a);

            int skillsCountCell = 6;

            while (row.getCell(skillsCountCell) != null) {

                String skillName = row.getCell(skillsCountCell).getStringCellValue();

                ApplicantSkill applicantSkill = new ApplicantSkill();
                applicantSkill.setApplicant(a);

                Skill skill;
                try {
                    skill = skillService.findSkillByName(skillName);
                } catch (SkillNotFoundException e) {
                    skill = new Skill();
                    skill.setNameOfSkill(skillName);
                    skillService.addSkill(skill);

                }

                logger.info("read an applicant skill from an exchel file and add in db");
                applicantSkill.setSkill(skill);
                applicantSkillList.add(applicantSkill);
                applicantSkillService.addApplicantSkill(a.getId(), skill.getId());

                skillsCountCell++;
            }
            a.setApplicantSkills(applicantSkillList);
        }
        return applicants;
    }
}