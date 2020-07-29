package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.ApplicantNotFoundException;
import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.Applicant;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;

import java.util.List;

public interface CreateAndMatchService {

    List<CreateAndMatch> getCreateAndMatchs();
  //  CreateAndMatch addCreateAndMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException;

    List<CreateAndMatch> autoCreateAndMatch();

    boolean autoCreateMatch();

}
