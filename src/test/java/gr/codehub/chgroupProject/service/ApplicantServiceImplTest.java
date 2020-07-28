package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicantServiceImplTest {

    private Object ApplicantSkill;

    @Autowired
    ApplicantService appl;

    @Test
    void getApplicant() throws ApplicantNotFoundException, ApplicantNotValidFields {
        Applicant a1 = new Applicant();
        a1.setFirstName("Kalis");
        a1.setLastName("Kolia");
        a1.setAvailable(true);
        a1.setRegion("Athens");
        a1.setEducation("IT");
        a1.setAddress("Kolokotroni 1");
        a1.setId(1);

        appl.addApplicant(a1);
        appl.addApplicant(a1);

        assertEquals(1, appl.getApplicants().size());

    }

    @Test
    void addApplicant() throws ApplicantNotFoundException, ApplicantNotValidFields {
        List<Applicant> applicant = new ArrayList<>();
        Applicant a1 = new Applicant();
        a1.setFirstName("Kalis");
        a1.setLastName("Kolia");
        a1.setAvailable(true);
        a1.setRegion("Athens");
        a1.setEducation("IT");
        a1.setAddress("Kolokotroni 1");
        a1.setId(1);

        Applicant a2 = new Applicant();
        a1.setFirstName("KalisF");
        a1.setLastName("KoliaGT");
        a1.setAvailable(true);
        a1.setRegion("Crete");
        a1.setEducation("IT");
        a1.setAddress("Kolokotroni 45");
        a1.setId(2);


        applicant.add(appl.addApplicant(a1));
        applicant.add(appl.addApplicant(a2));

        assertEquals(2, applicant.size());
    }

    @Test
    void testGetApplicant() throws ApplicantNotFoundException, ApplicantNotValidFields {

        Applicant a1 = new Applicant();
        a1.setFirstName("Kalis");
        a1.setLastName("Kolia");
        a1.setAvailable(true);
        a1.setRegion("Athens");
        a1.setEducation("IT");
        a1.setAddress("Kolokotroni 1");
        a1.setId(1);

        appl.addApplicant(a1);
        assertEquals("Athens", appl.getApplicantById(1).getRegion());

    }
}