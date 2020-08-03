package gr.codehub.chgroupProject.repository;


import gr.codehub.chgroupProject.dto.JobOfferRequiredSkillDTO;
import gr.codehub.chgroupProject.dto.JobOfferSkillDTO;
import gr.codehub.chgroupProject.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {

    @Query(value = "SELECT * " +
            "FROM JobOffer j " +
            "WHERE j.companyName = ?1", nativeQuery = true)
    List<JobOffer> findCompanyName(String companyName);//briskei ton customer analoga me to onoma tou

    @Query(value = "SELECT * " +
            "FROM JobOffer j " +
            "WHERE j.region = ?1", nativeQuery = true)
    List<JobOffer> findRegion(String region);

    @Query(value = "SELECT * " +
            "FROM JobOffer j " +
            "Inner join JobOfferSkill jos " +
            "ON j.id = jos.jobOffer_id " +
            "Inner Join Skill s " +
            "On s.id = jos.skill_id " +
            "where s.nameOfSkill = ?1 ", nativeQuery = true)
    List<JobOffer> findListOfJobOfferWithSkillName(String nameOfSkill);


    @Query(value = "SELECT TOP(20) sk.nameOfSkill as SkillName, COUNT(*) as howManyTimesAppeared FROM JobOfferSkill js, Skill sk where js.skill_id = sk.id group by sk.nameOfSkill ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<JobOfferRequiredSkillDTO> howManyTimesSkillAppearsInJobOfferSkills();

//********************************************************************************
@Query(value = "SELECT j.companyName as companyName, j.available as available, j.dateOfJobOffer as dateOfJobOffer, " +
        "j.level as level, j.position as position, j.region as region, s.nameOfSkill as nameOfSkill " +
        "FROM JobOffer j " +
        "Inner join JobOfferSkill jos " +
        "ON j.id = jos.jobOffer_id " +
        "Inner Join Skill s " +
        "On s.id = jos.skill_id " +
        "where j.companyName = ?1 " +
        "and j.region = ?2 " +
        "and s.nameOfSkill = ?3 ", nativeQuery = true)
List<JobOfferSkillDTO> findJobOfferByCompanyNameRegionSkillName(String companyName, String region, String nameOfSkill);

    @Query(value = "SELECT * " +
            "FROM JobOffer j " +
            "where j.companyName = ?1 " +
            "and j.region = ?2 ", nativeQuery = true)
    List<JobOfferSkillDTO> findJobOfferByCompanyNameANDRegion(String companyName, String region);

    @Query(value = "SELECT * " +
            "FROM JobOffer j " +
            "where j.companyName = ?1 " +
            "or j.region = ?2 ", nativeQuery = true)
    List<JobOfferSkillDTO> findJobOfferByCompanyNameOrRegion(String companyName, String region);

    @Query(value = "SELECT j.companyName as companyName, j.available as available, j.dateOfJobOffer as dateOfJobOffer, " +
            "j.level as level, j.position as position, j.region as region, s.nameOfSkill as nameOfSkill " +
            "FROM JobOffer j " +
            "Inner join JobOfferSkill jos " +
            "ON j.id = jos.jobOffer_id " +
            "Inner Join Skill s " +
            "On s.id = jos.skill_id " +
            "where j.companyName = ?1 " +
            "and s.nameOfSkill = ?2 ", nativeQuery = true)
    List<JobOfferSkillDTO> findJobOfferByCompanyNameAndSkillName(String companyName, String nameOfSkill);

    @Query(value = "SELECT j.companyName as companyName, j.available as available, j.dateOfJobOffer as dateOfJobOffer, " +
            "j.level as level, j.position as position, j.region as region, s.nameOfSkill as nameOfSkill " +
            "FROM JobOffer j " +
            "Inner join JobOfferSkill jos " +
            "ON j.id = jos.jobOffer_id " +
            "Inner Join Skill s " +
            "On s.id = jos.skill_id " +
            "where j.region = ?1 " +
            "and s.nameOfSkill = ?2 ", nativeQuery = true)
    List<JobOfferSkillDTO> findJobOfferByRegionAndSkillName(String region, String nameOfSkill);

    @Query(value = "SELECT j.companyName as companyName, j.available as available, j.dateOfJobOffer as dateOfJobOffer, " +
            "j.level as level, j.position as position, j.region as region, s.nameOfSkill as nameOfSkill " +
            "FROM JobOffer j " +
            "Inner join JobOfferSkill jos " +
            "ON j.id = jos.jobOffer_id " +
            "Inner Join Skill s " +
            "On s.id = jos.skill_id " +
            "where s.nameOfSkill = ?1 ", nativeQuery = true)
    List<JobOfferSkillDTO> jobOfferRepo(String nameOfSkill);

    @Query(value = "SELECT * FROM JobOffer j", nativeQuery = true)
    List<JobOfferSkillDTO> jobOfferListDTO();

}