package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.util.IO;
import gr.codehub.chgroupProject.util.ReadApplicants;
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

    Workbook workbook = IO.createWorkbook("data for rs-api.xlsx");

    public MainController() throws IOException, InvalidFormatException {
    }

    @GetMapping("createDb")
    public List<Applicant> createDb() throws IOException, SkillNotFoundException {
        return readApplicants.readApplicantsFromExcel(workbook);
    }
}
