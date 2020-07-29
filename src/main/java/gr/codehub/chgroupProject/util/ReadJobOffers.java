package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.JobOfferSkill;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.service.JobOfferService;
import gr.codehub.chgroupProject.service.JobOfferSkillService;
import gr.codehub.chgroupProject.service.SkillService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ReadJobOffers {

    private JobOfferService jobOfferService;
    private SkillService skillService;
    private JobOfferSkillService jobOfferSkillService;

    @Autowired
    public ReadJobOffers(JobOfferService jobOfferService, SkillService skillService, JobOfferSkillService jobOfferSkillService) {
        this.jobOfferService = jobOfferService;
        this.skillService = skillService;
        this.jobOfferSkillService = jobOfferSkillService;
    }

    public List<JobOffer> ReadJobOffersFromExcel(Workbook workbook) throws IOException, JobOfferNotFoundException, JobOfferNotValidFields, SkillNotFoundException, SkillNotValidFields {

        Sheet sheet = workbook.getSheetAt(1);

        List<JobOffer> jobOffers = new ArrayList<>();
        boolean firstTime = true;

        List<List<Integer>> jobOfferSkillsIdList = new ArrayList<>(); //NEW

        for (Row row : sheet) {
            if (firstTime) {
                firstTime = false;
                continue;
            }

            List<JobOfferSkill> jobOfferSkillList = new ArrayList<>();

            JobOffer jb = new JobOffer();
            jb.setCompanyName(row.getCell(0).getStringCellValue());
            jb.setPosition(row.getCell(1).getStringCellValue());
            jb.setRegion(row.getCell(2).getStringCellValue());
            jb.setLevel(row.getCell(3).getStringCellValue());
            jb.setAvailable(true);

            jobOffers.add(jb);
            jobOfferService.addJobOffer(jb);

            int skillsCountCell = 4;

            List<Integer> skillIdList = new ArrayList<>(); //NEW
            jobOfferSkillsIdList.add(skillIdList);  // NEW

            while (row.getCell(skillsCountCell) != null) {

                String skillName = row.getCell(skillsCountCell).getStringCellValue();

                JobOfferSkill jobOfferSkill = new JobOfferSkill();
                jobOfferSkill.setJobOffer(jb);

                Skill skill;

                try {
                    skill = skillService.findSkillByName(skillName);
                } catch (SkillNotFoundException e) {
                    skill = new Skill();
                    skill.setNameOfSkill(skillName);
                    skillService.addSkill(skill);

                }

                skillIdList.add(skill.getId()); //NEW

                jobOfferSkill.setSkill(skill);
                jobOfferSkillList.add(jobOfferSkill);
                jobOfferSkillService.addJobOfferSkill(jb.getId(), skill.getId());

                skillsCountCell++;

            }
            jb.setJobOfferSkill(jobOfferSkillList);
        }

        //System.out.println("************" + jobOfferSkillsIdList);
        return jobOffers;
    }
}
