package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.SkillService;
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

@Service
@Slf4j
public class ReadSkills {

    Logger logger = LoggerFactory.getLogger(ReadSkills.class);

    @Autowired
    private SkillService skillService;

    /**
     * Read skills from excel and them in db
     *
     * @param workbook
     * @return
     * @throws IOException
     * @throws SkillNotFoundException
     * @throws SkillNotValidFields
     */
    public List<Skill> readSkillsFromExcel(Workbook workbook) throws IOException, SkillNotFoundException, SkillNotValidFields {

        logger.info("Read a skill from an exchel file and add in db");

        List<Skill> skills = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(2);
        boolean firstTime = true;

        for (Row row : sheet) {
            if (firstTime) {
                firstTime = false;
                continue;
            }
            Skill skill = new Skill();
            skill.setNameOfSkill(row.getCell(0).getStringCellValue());

            skills.add(skill);
            skillService.addSkill(skill);
        }
        return skills;
    }
}