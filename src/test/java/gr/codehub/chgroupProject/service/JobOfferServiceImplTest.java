package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotValidFields;
import gr.codehub.chgroupProject.model.JobOffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JobOfferServiceImplTest {

    @Autowired
    JobOfferService jobOfferService;

    @Test
    void getJobOffers() throws JobOfferNotFoundException, JobOfferNotValidFields {

//        List<JobOffer> jobOffers1 = jobOfferService.getJobOffers();
//        assertEquals(0, jobOffers1.size());

        JobOffer jo0 = new JobOffer();
        jo0.setPosition("accentue");
        jo0.setAvailable(true);
        jo0.setRegion("athens");
        jo0.setCompanyName("Accenture");
        jo0.setLevel("Mid");


        jobOfferService.addJobOffer(jo0);
        List<JobOffer> jobOffers = jobOfferService.getJobOffers();


        assertEquals(1, jobOffers.size());
    }

    @Test
    void addJobOffer() throws JobOfferNotFoundException, JobOfferNotValidFields {

        JobOffer jo1 = new JobOffer();
        jo1.setPosition("accentue");
        jo1.setAvailable(true);
        jo1.setRegion("athens");
        jo1.setCompanyName("Accenture");
        jo1.setLevel("Mid");

        jobOfferService.addJobOffer(jo1);
        List<JobOffer> jobOffers1 = jobOfferService.getJobOffers();

        //jos.addJobOffer(jo1) kaloume thn sunarthsh apo to service pou epistrefei jobOffer

          //epistrefoun mia job offer opote gia na dw oti trexei kala h sunarthsh 8a ta balw sthn arraylist pou eftia3a
        assertEquals(1, jobOffers1.size());
    }

//    @Test
//    void updateJobOffer() {
//    }

    @Test
    void getJobOffer() throws JobOfferNotFoundException, JobOfferNotValidFields {

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

        int id = 3;
        JobOffer app= jobOfferService.getJobOfferById(3);

        assertThat(app.getId()).isEqualTo(id);
    }
}