package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicantServiceImplTest {
    @Autowired
    private ApplicantService applicantService;
    @Test
    void getApplicant() throws ApplicantNotFoundException, ApplicantNotValidFields {
        List<Applicant> applicants1 = applicantService.getApplicants();
        assertEquals(0, applicants1.size());
        Applicant applicant = new Applicant();
        applicant.setFirstName("John");
        applicant.setLastName("Kikl");
        applicant.setAddress("Johngt 21");
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setLevel("Junior");
        applicant.setAvailable(true);
        applicantService.addApplicant(applicant);
        List<Applicant> applicants = applicantService.getApplicants();
        assertEquals(1, applicants.size());
    }
    @Test
    void addApplicant() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant a1 = new Applicant();
        a1.setFirstName("Kalis");
        a1.setLastName("Kolia");
        a1.setAvailable(true);
        a1.setRegion("Athens");
        a1.setEducation("IT");
        a1.setAddress("Kolokotroni 1");
        a1.setLevel("Junior");
        Applicant a2 = new Applicant();
        a2.setFirstName("KalisF");
        a2.setLastName("KoliaGT");
        a2.setAvailable(true);
        a2.setRegion("Crete");
        a2.setEducation("IT");
        a2.setAddress("Kolokotroni 45");
        a2.setLevel("Mid");
        applicantService.addApplicant(a1);
        applicantService.addApplicant(a2);
        List<Applicant> applicants2 = applicantService.getApplicants();
        assertEquals(4, applicants2.size());
    }
    @Test
    void getApplicantById() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant applicant = new Applicant();
        applicant.setFirstName("John");
        applicant.setLastName("Kikl");
        applicant.setAddress("Johngt 21");
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setLevel("Junior");
        applicant.setAvailable(true);
        applicantService.addApplicant(applicant);
        int id = 1;
        Applicant app= applicantService.getApplicantById(1);
        assertThat(app.getId()).isEqualTo(id);
    }


}
