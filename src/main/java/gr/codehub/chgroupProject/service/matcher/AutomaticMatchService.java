package gr.codehub.chgroupProject.service.matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;

import java.util.List;

public interface AutomaticMatchService {
    List<CreateAndMatch> DoAutomaticMatch(boolean partial)
            throws ApplicantNotFoundException, JobOfferNotFoundException, SkillNotFoundException ;

}
