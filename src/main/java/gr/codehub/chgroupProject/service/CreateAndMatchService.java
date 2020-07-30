package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.List;
import java.util.Optional;

public interface CreateAndMatchService {
    List<CreateAndMatch> getCreateAndMatches();
    CreateAndMatch addCreateAndMatch (int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;
    CreateAndMatch updateCreateAndMatch(CreateAndMatch createAndMatch, int createAndMatchId) throws CreateAndMatchNotFound;
   // boolean checkIfApplicantIdAndJobOfferIdExist(int applicantId, int jobOfferId);
    boolean checkIfApplicantIdAndJobOfferIdExist(Applicant applicant, JobOffer jobOffer);
    CreateAndMatch findCreateAndMatch (int createAndMatch) throws CreateAndMatchNotFound;
}
