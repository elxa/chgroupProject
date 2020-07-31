package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO UPDATE , EXTEPTIONSAPPLICANT, TODO APPLICANT UPDATE AVAILABLE

/**
 * We directly call the ApplicantRepo
 *
 * @Service
 */
@Service
@Slf4j
public class ApplicantServiceImpl implements ApplicantService {

    Logger logger = LoggerFactory.getLogger(ApplicantServiceImpl.class);
    @Autowired
    private ApplicantRepository applicantRepo;

    /**
     * We call the methods of the Interfect
     *
     * @return Return the List of the Applicants from the dattabase
     * @Override
     */
//    @Override
//    public List<Applicant> getApplicants() {
//        logger.info("Get a list of Applicants from db");
//
//        return applicantRepo.findAll();
//    }
    @Override
    public List<Applicant> getApplicants(String firstName, String lastName, String dateOfRegister) throws ApplicantNotFoundException {
        logger.info("Get a list of Applicants from db");

        List<Applicant> applicants = new ArrayList<>();

        if (firstName != null) {
            Optional<Applicant> oApplicant = applicantRepo.findApplicantByFirstName(firstName);
            if (oApplicant.isPresent()) { //ean uparxei epistrefei to jobOffer
                applicants.add(oApplicant.get());
                logger.info("Return an applicant by name");
                return applicants;
            } else throw new ApplicantNotFoundException("Applicant with this name Not Found");
        }

        if (lastName != null) {
            Optional<Applicant> oApplicant = applicantRepo.findApplicantByLastName(lastName);
            if (oApplicant.isPresent()) { //ean uparxei epistrefei to jobOffer
                applicants.add(oApplicant.get());
                logger.info("Return an applicant by lastName");
                return applicants;
            } else throw new ApplicantNotFoundException("Applicant with this name Not Found");
        }
        if (dateOfRegister != null) {
                return applicantRepo.findApplicantListWithDateOfRegister();
        }
        logger.info("Return a list of applicants");
        return applicantRepo.findAll();
    }


    /**
     * @param applicant
     * @return
     * @throws ApplicantNotFoundException
     * @throws ApplicantNotValidFields
     */
    //todo prepei na tsekaroume an o xrhsths bazei swsta thn hmeromhnia alliws exei la8os
    @Override
    public Applicant addApplicant(Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields {
        logger.info("Add an applicant in db");
        if (applicant == null)
            throw new ApplicantNotFoundException("Applicant Not found");
        if (applicant.getFirstName() == null || applicant.getFirstName().equals("")
                || applicant.getLastName() == null || applicant.getLastName().equals("")
                || applicant.getAddress() == null || applicant.getAddress().equals("")
                || applicant.getRegion() == null || applicant.getEducation().equals("")
                || applicant.getEducation() == null || applicant.getEducation().equals("")
                || applicant.getLevel() == null || applicant.getLevel().equals("")
                || applicant.getAvailable() == null || applicant.getAvailable().equals("")
        )
            throw new ApplicantNotValidFields("Applicant fields must not be null");
        applicant.setDateOfApplicant(LocalDateTime.now());
        return applicantRepo.save(applicant);
    }

    @Override
    public Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException {
        logger.info("Update an applicant in db");

        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        () -> new ApplicantNotFoundException("Applicant Not Found"));
//todo na dw an xreiazetai gia ola ta pedia
        applicantInDb.setAvailable(applicant.getAvailable());
        applicantRepo.save(applicantInDb);

        return applicantInDb;
    }

    /**
     * @param applicantId specific applicant
     * @return Return a specific applicant.
     */
    @Override
    public Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException {
        logger.info("Get applicant in db");
        Optional<Applicant> oApplicant = applicantRepo.findById(applicantId);
        if (oApplicant.isPresent()) {
            return oApplicant.get();
        }
        else throw new ApplicantNotFoundException("Applicant Not Found"); //todo diorthwshh na bei to else

    }
}
