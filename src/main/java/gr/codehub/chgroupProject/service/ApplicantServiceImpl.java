package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.dto.ApplicantSkillDto;
import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.ApplicantNotValidFields;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.repository.ApplicantRepository;
import gr.codehub.chgroupProject.repository.ApplicantSkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ApplicantSkillRepository applicantSkillRepo;

    /**
     * We call the methods of the Interfect
     *
     * @return Return the List of the Applicants from the dattabase
     * @Override
     */

    /**
     * A list of applicants
     *
     * @return a list of applicants
     * @throws ApplicantNotFoundException
     */
    public List<Applicant> getListApplicants() throws ApplicantNotFoundException {
        return applicantRepo.findAll();
    }

    /**
     * Return a list of Applicant depend, it depend on the parameter which user give in url(first Name, last Name, skill name)
     *
     * @param firstName
     * @param lastName
     * @param skillName
     * @return
     * @throws ApplicantNotFoundException
     */
    @Override
    public List<ApplicantSkillDto> getApplicants(String firstName, String lastName, String skillName) throws ApplicantNotFoundException {
        logger.info("Get a list of Applicants from db");
        if (firstName != null && lastName != null && skillName != null) {
            return applicantRepo.findApplicantByFullNameAndSkillName(firstName, lastName, skillName);
        }
        if (firstName != null && lastName != null && skillName == null) {
            return applicantRepo.findApplicantByName(firstName, lastName);
        }
        if ((firstName != null || lastName != null) && skillName == null) {
            return applicantRepo.findApplicantByFirstNameOrByLastName(firstName, lastName);
        }
        if (firstName != null && skillName != null) {
            return applicantRepo.findApplicantByFirstNameAndSkillName(firstName, skillName);
        }
        if (lastName != null && skillName != null) {
            return applicantRepo.findApplicantByLastNameAndSkillName(lastName, skillName);
        }
        if (skillName != null && firstName == null && lastName == null) {
            return applicantRepo.findApplicantsBySkillName(skillName);
        }
        logger.info("Return a list of applicants");
        return applicantRepo.findAllApplicant();
    }

    /**
     * Add an applicant in db
     *
     * @param applicant
     * @return
     * @throws ApplicantNotFoundException
     * @throws ApplicantNotValidFields
     */
    @Override
    public Applicant addApplicant(Applicant applicant) throws ApplicantNotFoundException, ApplicantNotValidFields {
        logger.info("Add an applicant in db");
        if (applicant == null)
            throw new ApplicantNotFoundException("Applicant Not found");
        if (StringUtils.isEmpty(applicant.getFirstName())
                || StringUtils.isEmpty(applicant.getLastName())
                || StringUtils.isEmpty(applicant.getAddress())
                || StringUtils.isEmpty(applicant.getRegion())
                || StringUtils.isEmpty(applicant.getEducation())
                || StringUtils.isEmpty(applicant.getLevel())
                || StringUtils.isEmpty(applicant.getAvailable())
        )
            throw new ApplicantNotValidFields("Applicant fields must not be null");
        applicant.setDateOfApplicant(LocalDate.now());
        return applicantRepo.save(applicant);
    }

    /**
     * Update an applicant
     *
     * @param applicant
     * @param applicantId
     * @return
     * @throws ApplicantNotFoundException
     */
    @Override
    public Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException {
        logger.info("Update an applicant in db");

        Applicant applicantInDb = applicantRepo.findById(applicantId)
                .orElseThrow(
                        () -> new ApplicantNotFoundException("Applicant Not Found"));

        applicantInDb.setAvailable(applicant.getAvailable());
        applicantRepo.save(applicantInDb);

        return applicantInDb;
    }

    /**
     * Retrieve an applicant from db by id
     *
     * @param applicantId specific applicant
     * @return Return a specific applicant.
     */
    @Override
    public Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException {
        logger.info("Retrieve an applicant from db by id");
        Optional<Applicant> oApplicant = applicantRepo.findById(applicantId);
        if (oApplicant.isPresent()) {
            return oApplicant.get();
        } else throw new ApplicantNotFoundException("Applicant Not Found"); //todo diorthwshh na bei to else
    }
}
