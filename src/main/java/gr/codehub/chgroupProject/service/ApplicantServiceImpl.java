package gr.codehub.chgroupProject.service;

<<<<<<< HEAD
import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO UPDATE , EXTEPTIONSAPPLICANT, TODO APPLICANT UPDATE AVAILABLE

/**
 * We directly call the ApplicantRepo
 * @Service
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepo;

    /**
     *
     * We call the methods of the Interfect
     * @Override
     *
     * @return Return the List of the Applicants from the dattabase
     */
    @Override
    public List<Applicant> getApplicant() {
        return applicantRepo.findAll();
    }

    /**
     *
     * @param applicant
     * @return add the applicant on the datatbase
     */
    @Override
    public Applicant addApplicant(Applicant applicant) {
//        if (applicant == null)
        //throw new ApplicantCreationException("null customer");
//        if (applicant.getEmail()==null  || !customer.getEmail().contains("@")    )
//            throw new CustomerCreationException("invalid customer's email");
        return applicantRepo.save(applicant);
    }

//    @Override
//    public Applicant updateApplicant(Applicant applicant, int applicantId) {
//
//        Applicant applicantInDb = applicantRepo.findById(applicantId).get();
//              .orElseThrow(
//                       () -> new CustomerNotFoundException("not such customer")
//               );
//
//        applicantInDb.setFirstName(applicant.getFirstName());
//        applicantInDb.setLastName(applicant.getLastName());
//        applicantInDb.setAddress(applicant.getAddress());
//        applicantInDb.setRegion(applicant.getRegion());
//        applicantInDb.setEducation(applicant.getEducation());
//
////
//        applicantRepo.save(applicantInDb);
//
//        return applicantInDb;
//    }


    @Override
    public Applicant addApplicant(Applicant applicant) {
//        if (applicant == null)
        //throw new ApplicantCreationException("null customer");
//        if (applicant.getEmail()==null  || !customer.getEmail().contains("@")    )
//            throw new CustomerCreationException("invalid customer's email");
        return applicantRepo.save(applicant);
    }

//    @Override
//    public Applicant updateApplicant(Applicant applicant, int applicantId) {
//
//        Applicant applicantInDb = applicantRepo.findById(applicantId).get();
//              .orElseThrow(
//                       () -> new CustomerNotFoundException("not such customer")
//               );
//
//        applicantInDb.setFirstName(applicant.getFirstName());
//        applicantInDb.setLastName(applicant.getLastName());
//        applicantInDb.setAddress(applicant.getAddress());
//        applicantInDb.setRegion(applicant.getRegion());
//        applicantInDb.setEducation(applicant.getEducation());
//
////
//        applicantRepo.save(applicantInDb);
//
//        return applicantInDb;
//    }


    /**
     *
     * @param applicantId specific applicant
     * @return Return a specific applicant.
     */
    @Override
    public Applicant getApplicant(int applicantId) {
        Optional<Applicant> oApplicant =
                applicantRepo.findById(applicantId);
        if (oApplicant.isPresent()) {
            return oApplicant.get();
        }
        return oApplicant.get(); //todo diorthwshh na bei to else
//        else throw new ApplicantNotFoundException("Not Such Applicant");
        @Override
        public Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws
        ApplicantNotFoundException {

        Applicant applicantInDb = applicantRepo.findApplicantByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not found"));

        return applicantInDb;
    }
}
