package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;

import java.util.List;
import java.util.Optional;

public interface CreateAndMatchService {
    List<CreateAndMatch> getCreateAndMatches();
    CreateAndMatch addCreateAndMatch (int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;
    CreateAndMatch updateCreateAndMatch(CreateAndMatch createAndMatch, int createAndMatchId) throws CreateAndMatchNotFound;
   // boolean checkIfApplicantIdAndJobOfferIdExist(int applicantId, int jobOfferId);
}
