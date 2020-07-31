package gr.codehub.chgroupProject.controller.MacherController;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.service.Matcher.ManualMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo na ftia3oume ta end points
@RestController
public class ManualMatchContoller {

    @Autowired
    private ManualMatchService manualMatchService;

    @GetMapping("manualMatches")
    public List<CreateAndMatch> getmanuallMatches() {
        return manualMatchService.getManualMatches();
    }

    @PostMapping("createAndMatch/{applicantId}/{jobOfferId}")
    public CreateAndMatch addCreateAndMatch(@PathVariable int applicantId, @PathVariable int jobOfferId)
            throws ApplicantNotFoundException, JobOfferNotFoundException {
        return manualMatchService.addCreateAndMatchManual(applicantId, jobOfferId);
    }//    @GetMapping("createAndMatch/{createAndMatchId}")

    //    public CreateAndMatch getCreateAndMatchById(@PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
//        return createAndMatchService.getCreateAndMatchById(createAndMatchId);
//    }    @PutMapping("createAndMatch/{createAndMatchId}")
    public CreateAndMatch updateCreateAndMatch(@RequestBody CreateAndMatch createAndMatch, @PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
        return manualMatchService.updateCreateAndMatch(createAndMatch, createAndMatchId);
    }

    @DeleteMapping("manualMatches/{manualMatchesId}")
    public boolean deleteManualMatch(@PathVariable int manualMatchesId) throws CreateAndMatchNotFound {
        return manualMatchService.deleteCreateAndMatch(manualMatchesId);
    }
}