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
public class ReadJobOffers {

    Logger logger = LoggerFactory.getLogger(ReadJobOffers.class);


    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobOfferSkillService jobOfferSkillService;

    /**
     * Read job offers from excel and them in db
     *
     * @param workbook
     * @return
     * @throws IOException
     * @throws JobOfferNotFoundException
     * @throws JobOfferNotValidFields
     * @throws SkillNotFoundException
     * @throws SkillNotValidFields
     */
    public List<JobOffer> readJobOffersFromExcel(Workbook workbook) throws IOException, JobOfferNotFoundException, JobOfferNotValidFields, SkillNotFoundException, SkillNotValidFields {

        logger.info("Read a job offer from an exchel file and add in db");
        Sheet sheet = workbook.getSheetAt(1);

        List<JobOffer> jobOffers = new ArrayList<>();
        boolean firstTime = true;

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

            while (row.getCell(skillsCountCell) != null) {

                logger.info("read an job offer skill from an exchel file and add in db");
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
                jobOfferSkill.setSkill(skill);
                jobOfferSkillList.add(jobOfferSkill);
                jobOfferSkillService.addJobOfferSkill(jb.getId(), skill.getId());

                skillsCountCell++;
            }
            jb.setJobOfferSkill(jobOfferSkillList);
        }
        return jobOffers;
    }
}

