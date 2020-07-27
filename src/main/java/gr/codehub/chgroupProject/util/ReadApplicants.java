package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.dto.SkillsDto;
import gr.codehub.chgroupProject.model.Applicant;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ReadApplicants{
public List<SkillsDto> readApplicantsFromExcel(Workbook workbook)throws IOException {

        List<SkillsDto> skillsDto = new ArrayList<SkillsDto>();
        Sheet sheet=workbook.getSheetAt(0);


        List<Applicant> applicants=new ArrayList<>();
        boolean firstTime=true;

        for(Row row:sheet){
        if(firstTime){
        firstTime=false;
        continue;
        }
        Applicant a=new Applicant();
        a.setFirstName(row.getCell(0).getStringCellValue());
        a.setLastName(row.getCell(1).getStringCellValue());
        a.setAddress(row.getCell(2).getStringCellValue());
        a.setRegion(row.getCell(3).getStringCellValue());
        a.setEducation(row.getCell(4).getStringCellValue());
        a.setLevel(row.getCell(5).getStringCellValue());
        a.setAvailable(true);

        applicants.add(a);
        int skills_count_cell=6;

        List<String> skills=new ArrayList<>();
        while(row.getCell(skills_count_cell)!=null){
                String skill = row.getCell(skills_count_cell).getStringCellValue();

        skills.add(row.getCell(skills_count_cell).getStringCellValue());
        skills_count_cell++;
        }

        skillsDto.add(new SkillsDto(a ,skills));
        //System.out.println(skills);
        }

        // Closing the workbook
        //workbook.close(); to kleinw ston appstartup

        return skillsDto;
        }
}