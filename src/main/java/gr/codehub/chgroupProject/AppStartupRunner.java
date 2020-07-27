package gr.codehub.chgroupProject;

import gr.codehub.chgroupProject.util.IO;
import gr.codehub.chgroupProject.util.ReadApplicants;
import gr.codehub.chgroupProject.util.ReadJobOffers;
import gr.codehub.chgroupProject.util.ReadSkills;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


    @Component
    public class AppStartupRunner implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {


            Workbook workbook = IO.createWorkbook("data for rs-api.xlsx");

            ReadSkills rs = new ReadSkills();
            System.out.println(rs.ReadSkillsFromExcel(workbook));

            ReadApplicants ra =  new ReadApplicants();
            System.out.println(ra.readApplicantsFromExcel(workbook));

            //ReadJobOffers rjo = new ReadJobOffers();
            //System.out.println(rjo.ReadJobOffersFromExcel(workbook));

            workbook.close();
        }
    }



