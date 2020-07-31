package gr.codehub.chgroupProject.controller.MacherController;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.service.Matcher.CreateManualMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo na ftia3oume ta end points
@RestController
public class ManualMatchContoller {
    @Autowired
    private CreateManualMatchService createManualMatchService;    //TODO GYRNAEI OLI THN VASI DEN XREAIZETAI

    @GetMapping("createAndMatch")
    public List<CreateAndMatch> getListOfCreateAndMatch() {
        return createManualMatchService.getCreateAndMatches();
    }

    @PostMapping("createAndMatch/{applicantId}/{jobOfferId}")
    public CreateAndMatch addCreateAndMatch(@PathVariable int applicantId, @PathVariable int jobOfferId)
            throws ApplicantNotFoundException, JobOfferNotFoundException {
        return createManualMatchService.addCreateAndMatch(applicantId, jobOfferId);
    }//    @GetMapping("createAndMatch/{createAndMatchId}")

    //    public CreateAndMatch getCreateAndMatchById(@PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
//        return createAndMatchService.getCreateAndMatchById(createAndMatchId);
//    }    @PutMapping("createAndMatch/{createAndMatchId}")
    public CreateAndMatch updateCreateAndMatch(@RequestBody CreateAndMatch createAndMatch, @PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
        return createManualMatchService.updateCreateAndMatch(createAndMatch, createAndMatchId);
    }
}