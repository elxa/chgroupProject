package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.dto.ApplicantOfferedSkillDTO;
import gr.codehub.chgroupProject.dto.ApplicantSkillDto;
import gr.codehub.chgroupProject.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Optional<Applicant> findApplicantByFirstName(String firstName);//briskei ton customer analoga me to onoma tou
    Optional<Applicant> findApplicantByLastName(String lastName);
    Optional<Applicant> findApplicantById(String lastName);

    @Query(value = "SELECT TOP(20) s.nameOfSkill as SkillName, COUNT(*) as howManyTimesAppeared FROM ApplicantSkill askill, Skill s WHERE askill.skill_id = s.id GROUP BY s.nameOfSkill ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<ApplicantOfferedSkillDTO> howManyTimesSkillAppearsInApplicantSkills();


    @Query(value = "select * " +
            "from Applicant a " +
            "where  a.firstName = ?1 " +
            "and lastName = ?2", nativeQuery = true)
    List<ApplicantSkillDto> findApplicantByName(String firstName, String lastName);

    @Query(value = "select * " +
            "from Applicant a " +
            "where  a.firstName = ?1 " +
            "or lastName = ?2", nativeQuery = true)
    List<ApplicantSkillDto> findApplicantByFirstNameOrByLastName(String firstName, String lastName);


    @Query(value = "SELECT a3.address as Address, a3.available as Available, a3.dateOfApplicant as dateOfApplicant, " +
            "a3.education as education, a3.firstName as firstName, a3.lastName as lastName, a3.level as level, a3.region as region, s3.nameOfSkill as nameSkill " +
            "from Applicant a3, ApplicantSkill askill3, Skill s3 " +
            "where a3.id = askill3.applicant_id " +
            "and askill3.skill_id = s3.id " +
            "and a3.firstName = ?1 " +
            "and a3.lastName = ?2 " +
            "and s3.nameOfSkill = ?3", nativeQuery = true)
    List<ApplicantSkillDto> findApplicantByFullNameAndSkillName(String firstName, String lastName, String skillName );

    @Query(value = "SELECT * FROM Applicant", nativeQuery = true)
    List<ApplicantSkillDto> findAllApplicant();


    @Query(value = "SELECT a.address as Address, a.available as Available, a.dateOfApplicant as dateOfApplicant, " +
            "a.education as education, a.firstName as firstName, a.lastName as lastName, a.level as level, a.region as region, s.nameOfSkill as nameSkill " +
            "from Applicant a, ApplicantSkill askill, Skill s " +
            "where a.id = askill.applicant_id " +
            "and askill.skill_id = s.id " +
            "and a.firstName = ?1 " +
            "and s.nameOfSkill = ?2", nativeQuery = true)
    List<ApplicantSkillDto> findApplicantByFirstNameAndSkillName(String firstName, String skillName );


    @Query(value = "SELECT a.address as Address, a.available as Available, a.dateOfApplicant as dateOfApplicant, " +
            "a.education as education, a.firstName as firstName, a.lastName as lastName, a.level as level, a.region as region, s.nameOfSkill as nameSkill " +
            "from Applicant a, ApplicantSkill askill, Skill s " +
            "where a.id = askill.applicant_id " +
            "and askill.skill_id = s.id " +
            "and a.LastName = ?1 " +
            "and s.nameOfSkill = ?2", nativeQuery = true)
    List<ApplicantSkillDto> findApplicantByLastNameAndSkillName(String lastName, String skillName );

    @Query(value = "SELECT a.address as Address, a.available as Available, a.dateOfApplicant as dateOfApplicant, " +
            "a.education as education, a.firstName as firstName, a.lastName as lastName, a.level as level, a.region as region, s.nameOfSkill as nameSkill " +
            "from Applicant a, ApplicantSkill askill, Skill s " +
            "where a.id = askill.applicant_id " +
            "and askill.skill_id = s.id " +
            "and s.nameOfSkill = ?1", nativeQuery = true)
    List<ApplicantSkillDto> findApplicantsBySkillName(String skillName);



}