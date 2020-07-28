package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.excheption.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;

import java.util.List;

public interface CreateAndMatchService {
    List<CreateAndMatch> getCreateAndMatch();
    CreateAndMatch addCreateAndMatch (int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;
    CreateAndMatch updateCreateAndMatch(CreateAndMatch createAndMatch, int createAndMatchId) throws CreateAndMatchNotFound;
    //CreateAndMatch getCreateAndMatchById(int createAndMatchId) throws CreateAndMatchNotFound;
}
