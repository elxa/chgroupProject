package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class ApplicantServiceImplTest {

    @Mock
    private ApplicantRepository applicantRepo;

    @InjectMocks
    private ApplicantService applicantService = new ApplicantServiceImpl();

//    @Test
//    void getApplicantsSuccessful() {
//        Applicant applicant1 = new Applicant();
//        applicant1.setId(1);
//        applicant1.setFirstName("John");
//        applicant1.setLastName("Kikl");
//        applicant1.setAddress("Johngt 21");
//        applicant1.setRegion("Athens");
//        applicant1.setEducation("IT");
//        applicant1.setLevel("Junior");
//        applicant1.setAvailable(true);
//        List<Applicant> applicants = Arrays.asList(applicant1);
//        when(applicantRepo.findAll()).thenReturn(applicants);
//        List<Applicant> applicantsRetrieved = applicantService.getApplicants();
//        assertEquals(1, applicantsRetrieved.size());
//    }

    @Test
    void addApplicantSuccessful() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Kalis");
        applicant1.setLastName("Kolia");
        applicant1.setAvailable(true);
        applicant1.setRegion("Athens");
        applicant1.setEducation("IT");
        applicant1.setAddress("Kolokotroni 1");
        applicant1.setLevel("Junior");
        when(applicantRepo.save(applicant1)).thenReturn(applicant1);
        Applicant applicant = applicantService.addApplicant(applicant1);
        assertNotNull(applicant);
        assertEquals(applicant1.getFirstName(), applicant.getFirstName());
    }

    @Test
    void addApplicantNotFoundException() {
        Applicant a1 = null;
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            applicantService.addApplicant(a1);
        });
    }

    @Test
    void addApplicantNotValidFieldsException() {
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
    void getApplicantByIdSuccessful() throws ApplicantNotFoundException {
        Applicant applicant1 = new Applicant();
        applicant1.setId(1);
        applicant1.setFirstName("Kalis");
        applicant1.setLastName("Kolia");
        applicant1.setAvailable(true);
        applicant1.setRegion("Athens");
        applicant1.setEducation("IT");
        applicant1.setAddress("Kolokotroni 1");
        applicant1.setLevel("Junior");
        Optional<Applicant> optionalApplicant = Optional.of(applicant1);
        when(applicantRepo.findById(1)).thenReturn(optionalApplicant);
        Applicant app = applicantService.getApplicantById(1);
        assertEquals(1, app.getId());
    }

    @Test
    void getApplicantByIdApplicantNotFound() {
        when(applicantRepo.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            applicantService.getApplicantById(1);
        });
    }

    @Test
    void updateApplicantSuccessful() throws ApplicantNotFoundException {
        Applicant applicant = new Applicant();
        applicant.setFirstName("Kalistffr");
        applicant.setLastName("Kolia");
        applicant.setAvailable(true);
        applicant.setRegion("Athens");
        applicant.setEducation("IT");
        applicant.setAddress("Kolokotroni 1");
        applicant.setLevel("Junior");
        when(applicantRepo.findById(1)).thenReturn(Optional.of(applicant));
        Applicant applicantDB = applicantService.updateApplicant(applicant, 1);
        assertTrue(applicantDB.getAvailable());
        applicant.setAvailable(false);
        applicantDB  = applicantService.updateApplicant(applicant,1);
        assertFalse(applicantDB.getAvailable());
    }

    @Test
    void updateApplicantByIdApplicantNotFound() {
        Assertions.assertThrows(ApplicantNotFoundException.class, () -> {
            applicantService.updateApplicant(new Applicant(), 10);
        });
    }
}