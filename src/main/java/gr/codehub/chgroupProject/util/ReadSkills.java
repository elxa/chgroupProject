//package gr.codehub.chgroupProject.util;
//
//import gr.codehub.chgroupProject.dto.SkillsDto;
//import gr.codehub.chgroupProject.model.Applicant;
//import gr.codehub.chgroupProject.model.JobOffer;
//import gr.codehub.chgroupProject.model.Skill;
//import org.apache.poi.ss.usermodel.*;
//
//import java.io.*;
//import java.util.*;
//
//
//public class ReadSkills {
//    public List<Skill> ReadSkillsFromExcel (Workbook workbook) throws IOException {
//
//        List<Skill> skills = new ArrayList<>();
//
//        Sheet sheet= workbook.getSheetAt(2);
//        boolean firstTime=true;
//
//        for(Row row:sheet){
//            if(firstTime){
//                firstTime=false;
//                continue;
//            }
//            Skill skill =new Skill();
//            skill.setNameOfSkill(row.getCell(0).getStringCellValue());
//
//            skills.add(skill);
//        }
//
//       // workbook.close(); to kleinw ston appstartup
//        return skills;
//    }
//}
