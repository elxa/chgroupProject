package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.service.CreateAndMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//todo na ftia3oume ta end points
@RestController
public class CreateAndMatchContoller {

    @Autowired
    private CreateAndMatchService createAndMatchService;

    /**
     * @return a list of all the matches
     */

    @GetMapping("createAndMatch")
    public List<CreateAndMatch> getListOfCreateAndMatch() {
        return createAndMatchService.getCreateAndMatch();
    }

    /**
     *
     * @param applicantId
     * @param jobOfferId
     * @return the list with the new match we created
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     */

    @PostMapping("createAndMatch")
    public CreateAndMatch addCreateAndMatch(@PathVariable int applicantId,@PathVariable int jobOfferId)
            throws ApplicantNotFoundException, JobOfferNotFoundException {
        return createAndMatchService.addCreateAndMatch(applicantId,jobOfferId);
    }

//    @GetMapping("createAndMatch/{createAndMatchId}")
//    public CreateAndMatch getCreateAndMatchById(@PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
//        return createAndMatchService.getCreateAndMatchById(createAndMatchId);
//    }

    /**
     *
     * @param createAndMatch
     * @param createAndMatchId
     * @return the updated createAndMatch list by their id
     * @throws CreateAndMatchNotFound
     */

    @PutMapping("createAndMatch/{createAndMatchId}")
    public CreateAndMatch updateCreateAndMatch(@RequestBody CreateAndMatch createAndMatch, @PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
        return createAndMatchService.updateCreateAndMatch(createAndMatch,createAndMatchId);
    }
}
