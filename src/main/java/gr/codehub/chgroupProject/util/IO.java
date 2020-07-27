package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.model.Applicant;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;


public class IO {
    public List<Applicant> readFromExcel(String filename) throws IOException, InvalidFormatException {

        List<Applicant> applicants = new ArrayList<>();
        File workbookFile = new File(filename);
        FileInputStream file = new FileInputStream(workbookFile);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        boolean firstTime = true;

        for (Row row : sheet) {
            if (firstTime) {
                firstTime = false;
                continue;
            }
            Applicant a =  new Applicant();
            a.setFirstName(row.getCell(0).getStringCellValue());
            a.setLastName(row.getCell(1).getStringCellValue());
            a.setAddress(row.getCell(2).getStringCellValue());
            a.setRegion(row.getCell(3).getStringCellValue());
            a.setEducation(row.getCell(4).getStringCellValue());

            String level= row.getCell(5).getStringCellValue();
            a.setAvailable(true);
            a.setApplicantSkills(null);
            applicants.add(a);
            int skills_count_cell=6;
            int skills_count=0;

            List<String> Skills = new ArrayList<>();
            while (row.getCell(skills_count_cell) != null){
                Skills.add(row.getCell(skills_count_cell).getStringCellValue());
                skills_count_cell++;
            }

            System.out.println(Skills);
        }

        // Closing the workbook
        workbook.close();

        return applicants;
    }
}
