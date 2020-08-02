package gr.codehub.chgroupProject.service.matcher;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.RetrieveDatesException;
import gr.codehub.chgroupProject.model.CreateAndMatch;

import java.util.List;

public interface FinalizeService {
    CreateAndMatch doFinalize(CreateAndMatch createAndMatch) throws CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException;

    List<CreateAndMatch> finalizedList();

    List<CreateAndMatch> weeklyAndMonthlyRecords(String startDate, String endDate) throws CreateAndMatchNotFound, RetrieveDatesException;
}
