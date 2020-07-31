package gr.codehub.chgroupProject.service;


import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

//TODO UPDATE , EXTEPTIONSAPPLICANT, TODO APPLICANT UPDATE AVAILABLE


@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepo;

    /**
     * In this method we get  all applicants
     * @return a list with all applicants
     */
    @Override
    public List<Applicant> getApplicants() {
        return applicantRepo.findAll();
    }


    //todo prepei na tsekaroume an o xrhsths bazei swsta thn hmeromhnia alliws exei la8os

    /**
     *
     * @param applicant
     * @return a certain applicant
     * @throws ApplicantNotFoundException
     * @throws ApplicantNotValidFields
     */
    @Override
    public Applicant addApplicant(Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields {
        if (applicant == null)
            throw new ApplicantNotFoundException("Applicant Not found");
        // StringUtils.isEmpty(applicant.getFirstName())
        if (StringUtils.isEmpty(applicant.getFirstName())
                || StringUtils.isEmpty(applicant.getLastName())
                || StringUtils.isEmpty(applicant.getAddress())
                || StringUtils.isEmpty(applicant.getRegion())
                || StringUtils.isEmpty(applicant.getEducation())
                || StringUtils.isEmpty(applicant.getLevel())
                || StringUtils.isEmpty(applicant.getAvailable())
        )
            throw new ApplicantNotValidFields("Applicant fields must not be null");
        return applicantRepo.save(applicant);
    }

    /**
     * We create a method in order to update the applicant
     * @param applicant
     * @param applicantId
     * @return applicantInDB
     * @throws ApplicantNotFoundException
     */

    @Override
    public Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException {
        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not Found"));
        //todo na dw an xreiazetai gia ola ta pedia
        applicantInDb.setAvailable(applicant.getAvailable());
        applicantRepo.save(applicantInDb);

        return applicantInDb;
    }

    /**
     * We create a method in order to get the id of applicant
     * @param applicantId
     * @return the applicant of id
     * @throws ApplicantNotFoundException
     */


    @Override
    public Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException {
        Optional<Applicant> oApplicant = applicantRepo.findById(applicantId);
        if (oApplicant.isPresent()) {
            return oApplicant.get();
        }
        else throw new ApplicantNotFoundException("Applicant Not Found"); //todo diorthwshh na bei to else

    }

//    @Override
//    public Applicant findApplicantByFirstNameAndLastName(String firstName, String lastName) throws ApplicantNotFoundException {
//
//        Applicant applicantInDb = applicantRepo.findApplicantByFirstNameAndLastName(firstName, lastName)
//                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not found"));
//
//        return applicantInDb;
//    }
}
