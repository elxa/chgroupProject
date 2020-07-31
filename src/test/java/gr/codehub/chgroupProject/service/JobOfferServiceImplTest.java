package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.JobOfferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class JobOfferServiceImplTest {

    @Mock
    private JobOfferRepository jobOfferRepository;
    @InjectMocks
    private JobOfferService jobOfferService = new JobOfferServiceImpl();

    @Test
    void getJobOffersSuccessful() {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1);
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        List<JobOffer> jobOffers = Arrays.asList(jobOffer);
        when(jobOfferRepository.findAll()).thenReturn(jobOffers);
        List<JobOffer> jobOffersRetrieved = jobOfferService.getJobOffers();
        assertEquals(1, jobOffersRetrieved.size());
    }

    @Test
    void addJobOffer() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jo1 = new JobOffer();
        jo1.setPosition("accentue");
        jo1.setAvailable(true);
        jo1.setRegion("athens");
        jo1.setCompanyName("Accenture");
        jo1.setLevel("Mid");
        when(jobOfferRepository.save(jo1)).thenReturn(jo1);
        JobOffer jobOffer = jobOfferService.addJobOffer(jo1);
        assertNotNull(jobOffer);
        assertEquals(jo1.getCompanyName(), jobOffer.getCompanyName());
    }

    @Test
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
        jo1.setId(1);
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
    void getJobOfferById() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jo2 = new JobOffer();
        jo2.setId(1);
        jo2.setPosition("accentue");
        jo2.setAvailable(true);
        jo2.setRegion("athens");
        jo2.setCompanyName("Accenture");
        jo2.setLevel("Mid");
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(jo2));
        JobOffer jobOffer = jobOfferService.getJobOfferById(1);
        assertEquals(1, jobOffer.getId());
    }

    @Test
    void getJobOfferByIdJobOfferNotFound() throws JobOfferNotFoundException, JobOfferNotValidFields {
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            JobOffer jobOffer = jobOfferService.getJobOfferById(10);
            assertThat(jobOffer.getId()).isEqualTo(10);
        });
    }

    @Test
    void updateJobOffer() throws JobOfferNotFoundException, JobOfferNotValidFields {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setPosition("accentue");
        jobOffer.setAvailable(true);
        jobOffer.setRegion("athens");
        jobOffer.setCompanyName("Accenture");
        jobOffer.setLevel("Mid");
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(jobOffer));
        JobOffer jobOfferDB = jobOfferService.updateJobOffer(jobOffer,1);
        assertTrue(jobOffer.getAvailable());
        jobOffer.setAvailable(false);
        assertFalse(jobOffer.getAvailable());
    }

    @Test
    @Transactional
    void updateJobOfferNotFound() throws JobOfferNotFoundException, JobOfferNotValidFields {
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            jobOfferService.updateJobOffer(new JobOffer(), 4);
        });
    }
}