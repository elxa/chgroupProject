package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JobOfferServiceImplTest {

    @Autowired
    JobOfferService jobOfferService;

    @Test
    @Transactional
    void getJobOffersSuccessful() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        jobOfferService.addJobOffer(jobOffer);
        List<JobOffer> jobOffers = jobOfferService.getJobOffers();
        assertEquals(1, jobOffers.size());
    }

    @Test
    @Transactional
    void addJobOffer() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jo1 = new JobOffer();
        jo1.setPosition("accentue");
        jo1.setAvailable(true);
        jo1.setRegion("athens");
        jo1.setCompanyName("Accenture");
        jo1.setLevel("Mid");
        jobOfferService.addJobOffer(jo1);
        List<JobOffer> jobOffers1 = jobOfferService.getJobOffers();
        assertEquals(1, jobOffers1.size());
    }

    @Test
    @Transactional
    void addJobOfferNotFoundException() {
        JobOffer jobOffer = null;
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            jobOfferService.addJobOffer(jobOffer);
        });
    }

    @Test
    @Transactional
    void addJobOfferNotValidFields() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jo1 = new JobOffer();
        jo1.setPosition("accentue");
        jo1.setAvailable(true);
        jo1.setRegion("");
        jo1.setCompanyName("Accenture");
        jo1.setLevel("Mid");
        Assertions.assertThrows(JobOfferNotValidFields.class, () -> {
            jobOfferService.addJobOffer(jo1);
        });
    }


    @Test
    @Transactional
    void getJobOfferById() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jo2 = new JobOffer();
        jo2.setPosition("accentue");
        jo2.setAvailable(true);
        jo2.setRegion("athens");
        jo2.setCompanyName("Accenture");
        jo2.setLevel("Mid");
        JobOffer jo3 = new JobOffer();
        jo3.setPosition("accentue");
        jo3.setAvailable(true);
        jo3.setRegion("athens");
        jo3.setCompanyName("Accenture");
        jo3.setLevel("Mid");
        jobOfferService.addJobOffer(jo2);
        jobOfferService.addJobOffer(jo3);
        JobOffer jobOffer = jobOfferService.getJobOfferById(1);
        assertThat(jobOffer.getId()).isEqualTo(1);
    }

    @Test
    @Transactional
    void getJobOfferByIdJobOfferNotFound() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jo2 = new JobOffer();
        jo2.setPosition("accentue");
        jo2.setAvailable(true);
        jo2.setRegion("athens");
        jo2.setCompanyName("Accenture");
        jo2.setLevel("Mid");
        jobOfferService.addJobOffer(jo2);
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            JobOffer jobOffer = jobOfferService.getJobOfferById(10);
            assertThat(jobOffer.getId()).isEqualTo(10);
        });
    }

    @Test
    @Transactional
    void updateJobOffer() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        jobOfferService.addJobOffer(jobOffer);
        List<JobOffer> jobOffers = jobOfferService.getJobOffers();
        assertEquals(1, jobOffers.size());
        jobOffer.setAvailable(false);
        jobOfferService.updateJobOffer(jobOffer, 5);
    }

    @Test
    @Transactional
    void updateJobOfferNotFound() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        jobOfferService.addJobOffer(jobOffer);
        List<JobOffer> jobOffers = jobOfferService.getJobOffers();
        assertEquals(1, jobOffers.size());
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            jobOffer.setAvailable(false);
            jobOfferService.updateJobOffer(jobOffer, 4);
        });
    }
}