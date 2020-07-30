package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;

public interface FinalizeService {
    CreateAndMatch doFinalize(CreateAndMatch createAndMatch) throws
            CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException;
}
