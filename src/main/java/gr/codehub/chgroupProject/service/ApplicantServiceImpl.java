package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO UPDATE , EXTEPTIONSAPPLICANT, TODO APPLICANT UPDATE AVAILABLE

/**
 * We directly call the ApplicantRepo
 *
 * @Service
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepo;

    /**
     * We call the methods of the Interfect
     *
     * @return Return the List of the Applicants from the dattabase
     * @Override
     */
    @Override
    public List<Applicant> getApplicants() {
        return applicantRepo.findAll();
    }

    /**
     * @param applicant
     * @return add the applicant on the datatbase
     */
    //todo prepei na tsekaroume an o xrhsths bazei swsta thn hmeromhnia alliws exei la8os
    @Override
    public Applicant addApplicant(Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields {
        if (applicant == null)
            throw new ApplicantNotFoundException("Applicant Not found");
        if (applicant.getAddress() == null || applicant.getAddress().equals("")
                || applicant.getFirstName() == null || applicant.getLastName().equals("")
                || applicant.getLastName() == null|| applicant.getFirstName().equals("")
                || applicant.getEducation() == null || applicant.getEducation().equals("")
                || applicant.getLevel() == null || applicant.getLevel().equals("")
                || applicant.getRegion() == null || applicant.getEducation().equals(""))
            throw new ApplicantNotValidFields("Applicant fields must not be null");
        return applicantRepo.save(applicant);
    }

    @Override
    public Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException {
        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        () -> new ApplicantNotFoundException("Applicant Not Found"));

        applicantInDb.setAvailable(false);

        applicantRepo.save(applicantInDb);

        return applicantInDb;
    }

    /**
     * @param applicantId specific applicant
     * @return Return a specific applicant.
     */
    @Override
    public Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException {
        Optional<Applicant> oApplicant = applicantRepo.findById(applicantId);
        if (oApplicant.isPresent()) {
            return oApplicant.get();
        }
        return oApplicant.orElseThrow(
                () -> new ApplicantNotFoundException("Applicant Not Found")); //todo diorthwshh na bei to else

    }

    @Override
    public Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException {

        Applicant applicantInDb = applicantRepo.findApplicantByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not found"));

        return applicantInDb;
    }
}
