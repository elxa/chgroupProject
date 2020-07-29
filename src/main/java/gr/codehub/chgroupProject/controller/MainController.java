package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.*;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.util.IO;
import gr.codehub.chgroupProject.util.ReadApplicants;
import gr.codehub.chgroupProject.util.ReadJobOffers;
import gr.codehub.chgroupProject.util.ReadSkills;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    ReadApplicants readApplicants;

    @Autowired
    ReadSkills readSkills;

    @Autowired
    ReadJobOffers readJobOffers;

    Workbook workbook = IO.createWorkbook("data for rs-api.xlsx");

    public MainController() throws IOException, InvalidFormatException {
    }

    @GetMapping("createDbSkills")
    public List<Skill> createDbSkills() throws IOException, SkillNotFoundException, SkillNotValidFields {
        return readSkills.ReadSkillsFromExcel(workbook);
    }

    @GetMapping("createDbApplicants")
    public List<Applicant> createDbApplicants() throws IOException, ApplicantNotFoundException, ApplicantNotValidFields, SkillNotFoundException, SkillNotValidFields {
        return readApplicants.readApplicantsFromExcel(workbook);
    }

    @GetMapping("createDbJobOffers")
    public List<JobOffer> createDbJobOffers() throws IOException, JobOfferNotFoundException, JobOfferNotValidFields, SkillNotFoundException, SkillNotValidFields {
        return readJobOffers.ReadJobOffersFromExcel(workbook);
    }


}

