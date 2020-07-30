package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ApplicantServiceImplTest {


    @Autowired
    private ApplicantService applicantService;


    @Test
    @Transactional
    void getApplicantsSuccessful() throws ApplicantNotValidFields, ApplicantNotFoundException {
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("John");
        applicant1.setLastName("Kikl");
        applicant1.setAddress("Johngt 21");
        applicant1.setRegion("Athens");
        applicant1.setEducation("IT");
        applicant1.setLevel("Junior");
        applicant1.setAvailable(true);
        applicantService.addApplicant(applicant1);
        List<Applicant> applicants = applicantService.getApplicants();
        assertEquals(1, applicants.size());
    }

    @Test
    @Transactional
    void addApplicantSuccessful() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Kalis");
        applicant1.setLastName("Kolia");
        applicant1.setAvailable(true);
        applicant1.setRegion("Athens");
        applicant1.setEducation("IT");
        applicant1.setAddress("Kolokotroni 1");
        applicant1.setLevel("Junior");
        applicantService.addApplicant(applicant1);
        List<Applicant> applicants2 = applicantService.getApplicants();
        assertEquals(1, applicants2.size());
        // asserts for newly added item

    }

    @Test
    @Transactional
    void getApplicantNotFoundException() {
        Applicant a1 = null;
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            applicantService.addApplicant(a1);
        });
    }

    @Test
    @Transactional
    void getApplicantNotValidExceptiond() throws ApplicantNotValidFields, ApplicantNotFoundException {
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Kalis");
        applicant1.setLastName("Kolia");
        applicant1.setAvailable(true);
        applicant1.setRegion("athens");
        applicant1.setEducation("");
        applicant1.setAddress("Kolokotroni 1");
        applicant1.setLevel("Junior");
        Assertions.assertThrows(ApplicantNotValidFields.class, () -> {
            applicantService.addApplicant(applicant1);
        });
    }

    @Test
    @Transactional
    void getApplicantByIdSuccessful() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Kalis");
        applicant1.setLastName("Kolia");
        applicant1.setAvailable(true);
        applicant1.setRegion("Athens");
        applicant1.setEducation("IT");
        applicant1.setAddress("Kolokotroni 1");
        applicant1.setLevel("Junior");
        applicantService.addApplicant(applicant1);
        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Kalis");
        applicant2.setLastName("Kolia");
        applicant2.setAvailable(true);
        applicant2.setRegion("Athens");
        applicant2.setEducation("IT");
        applicant2.setAddress("Kolokotroni 1");
        applicant2.setLevel("Junior");
        applicantService.addApplicant(applicant2);
        Applicant app = applicantService.getApplicantById(3);
        assertThat(app.getId()).isEqualTo(3);

    }

    @Test
    @Transactional
    void getApplicantByIdApplicantNotFound () throws ApplicantNotValidFields, ApplicantNotFoundException {
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Kalis");
        applicant1.setLastName("Kolia");
        applicant1.setAvailable(true);
        applicant1.setRegion("Athens");
        applicant1.setEducation("IT");
        applicant1.setAddress("Kolokotroni 1");
        applicant1.setLevel("Junior");
        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Kalis");
        applicant2.setLastName("Kolia");
        applicant2.setAvailable(true);
        applicant2.setRegion("Athens");
        applicant2.setEducation("IT");
        applicant2.setAddress("Kolokotroni 1");
        applicant2.setLevel("Junior");
        applicantService.addApplicant(applicant1);
        applicantService.addApplicant(applicant2);
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            Applicant app = applicantService.getApplicantById(10);
            assertThat(app.getId()).isEqualTo(10);
        });


    }

    @Test
    @Transactional
    void updateApplicantSuccessful () throws ApplicantNotValidFields, ApplicantNotFoundException {
        Applicant applicant = new Applicant();
        applicant.setFirstName("Kalistffr");
        applicant.setLastName("Kolia");
        applicant.setAvailable(true);
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setAddress("Kolokotroni 1");
        applicant.setLevel("Junior");
        applicantService.addApplicant(applicant);
        List<Applicant> applicants = applicantService.getApplicants();
        assertEquals(1, applicants.size());
        applicant.setAvailable(false);
        applicantService.updateApplicant(applicant, 2);
    }

    @Test
    @Transactional
    void updateApplicantByIdApplicantNotFound() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant applicant = new Applicant();
        applicant.setFirstName("Kalistffr");
        applicant.setLastName("Kolia");
        applicant.setAvailable(true);
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setAddress("Kolokotroni 1");
        applicant.setLevel("Junior");
        applicantService.addApplicant(applicant);
        List<Applicant> applicants = applicantService.getApplicants();
        assertEquals(1, applicants.size());
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            applicant.setAvailable(false);
            applicantService.updateApplicant(applicant, 10);
        });



        //applicants = applicantService.getApplicants();
        //assertTrue(applicants.get(1).getAvailable());

    }




}