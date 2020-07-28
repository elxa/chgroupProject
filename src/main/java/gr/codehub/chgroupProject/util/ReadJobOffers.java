package gr.codehub.chgroupProject.util;

//import gr.codehub.chgroupProject.dto.SkillsDto;
import gr.codehub.chgroupProject.model.JobOffer;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ReadJobOffers {
    public List<SkillsDto> ReadJobOffersFromExcel (Workbook workbook) throws IOException {

        List<SkillsDto> skillsDto = new ArrayList<SkillsDto>();

        Sheet sheet= workbook.getSheetAt(1);

        //List<JobOffer> jobOffers =new ArrayList<>();
        boolean firstTime=true;

        for(Row row:sheet){
            if(firstTime){
                firstTime=false;
                continue;
            }
            JobOffer jb =new JobOffer();
            jb.setName(row.getCell(0).getStringCellValue());
            jb.setPosition(row.getCell(1).getStringCellValue());
            jb.setRegion(row.getCell(2).getStringCellValue());
            jb.setLevel(row.getCell(3).getStringCellValue());
            jb.setAvailable(true);

            int skillsCountCell=4;

            List<String> skills=new ArrayList<>();
            while(row.getCell(skillsCountCell)!=null){
                skills.add(row.getCell(skillsCountCell).getStringCellValue());
                skillsCountCell++;
            }

            skillsDto.add(new SkillsDto(jb ,skills));
    }

        //workbook.close(); to kleinw ston appstartup
        return skillsDto;
    }
}
