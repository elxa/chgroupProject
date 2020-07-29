package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.SkillService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ReadSkills {

    @Autowired
    private SkillService skillService;

    public List<Skill> ReadSkillsFromExcel (Workbook workbook) throws IOException, SkillNotFoundException, SkillNotValidFields {

        List<Skill> skills = new ArrayList<>();
        Sheet sheet= workbook.getSheetAt(2);
        boolean firstTime=true;

        for(Row row:sheet){
            if(firstTime){
                firstTime=false;
                continue;
            }
            Skill skill =new Skill();
            skill.setNameOfSkill(row.getCell(0).getStringCellValue());

            skills.add(skill);
            skillService.addSkill(skill);

        }

        return skills;
    }
}
