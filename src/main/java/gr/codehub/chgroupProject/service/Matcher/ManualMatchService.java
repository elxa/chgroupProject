package gr.codehub.chgroupProject.service.Matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.List;

public interface ManualMatchService {
    List<CreateAndMatch> getManualMatches();

    CreateAndMatch addCreateAndMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;

    CreateAndMatch addCreateAndMatchManual(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;


    CreateAndMatch updateCreateAndMatch(CreateAndMatch createAndMatch, int createAndMatchId) throws CreateAndMatchNotFound;

    // boolean checkIfApplicantIdAndJobOfferIdExist(int applicantId, int jobOfferId);
    boolean checkIfApplicantIdAndJobOfferIdExist(Applicant applicant, JobOffer jobOffer);


    CreateAndMatch findCreateAndMatch(int createAndMatch) throws CreateAndMatchNotFound;

    List<CreateAndMatch> listOfManualCreateAndMatch();

    List<CreateAndMatch> listOfAutomaticCreateAndMatch();

//    List<CreateAndMatch> finalizedList();

    boolean deleteCreateAndMatch(int createAndMatchManualId) throws CreateAndMatchNotFound;
}
