package gr.codehub.chgroupProject.dto;
/**
 * a dto about jobOffer and skills
 */
public interface JobOfferSkillDTO {
    String getCompanyName();

    boolean getAvailable();

    String getDateOfJobOffer();

    String getLevel();

    String getPosition();

    String getRegion();

    String getNameOfSkill();
}
