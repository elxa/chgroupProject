package gr.codehub.chgroupProject.controller.matcherController;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.service.matcher.ManualMatchService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ManualMatchContoller {

    Logger logger = LoggerFactory.getLogger(ManualMatchContoller.class);

    @Autowired
    private ManualMatchService manualMatchService;

    /**
     * A list of manual Matches
     *
     * @return a list of manual Matches
     */
    @GetMapping("manualMatches")
    public List<CreateAndMatch> getmanuallMatches() {
        logger.info("A list of manual Matches");
        return manualMatchService.getManualMatches();
    }

    /**
     * Create a manual match
     *
     * @param applicantId
     * @param jobOfferId
     * @return
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     */
    @PostMapping("manualMatches/{applicantId}/{jobOfferId}")
    public CreateAndMatch addCreateAndMatch(@PathVariable int applicantId, @PathVariable int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException {
        logger.info("Create a manual match");
        return manualMatchService.addCreateAndMatchManual(applicantId, jobOfferId);
    }

    /**
     * Update a manual match
     *
     * @param createAndMatch
     * @param createAndMatchId
     * @return
     * @throws CreateAndMatchNotFound
     */

    @PutMapping("manualMatches/{createAndMatchId}")
    public CreateAndMatch updateCreateAndMatch(@RequestBody CreateAndMatch createAndMatch, @PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
        logger.info("Update a manual match");
        return manualMatchService.updateCreateAndMatch(createAndMatch, createAndMatchId);
    }

    /**
     * Delete a manual match
     *
     * @param manualMatchesId
     * @return
     * @throws CreateAndMatchNotFound
     */
    @DeleteMapping("manualMatches/{manualMatchesId}")
    public boolean deleteManualMatch(@PathVariable int manualMatchesId) throws CreateAndMatchNotFound {
        logger.info("Delete a manual match");
        return manualMatchService.deleteCreateAndMatch(manualMatchesId);
    }
}