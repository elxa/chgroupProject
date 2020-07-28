package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;

import java.util.List;

public interface CreateAndMatchService {
    List<CreateAndMatch> getCreateAndMatch();
    CreateAndMatch addCreateAndMatch (int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;
    CreateAndMatch updateCreateAndMatch(CreateAndMatch createAndMatch, int createAndMatchId) throws CreateAndMatchNotFound;
}
