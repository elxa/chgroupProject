package gr.codehub.chgroupProject.controller;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.model.JobOffer;
import gr.codehub.chgroupProject.service.CreateAndMatchService;
import gr.codehub.chgroupProject.service.JobOfferService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//todo na ftia3oume ta end points
@RestController
@Slf4j
public class CreateAndMatchContoller {
    Logger logger = LoggerFactory.getLogger(CreateAndMatchContoller.class);

    @Autowired
    private CreateAndMatchService createAndMatchService;

    @GetMapping("createAndMatch")
    public List<CreateAndMatch> getListOfCreateAndMatch() {
        logger.info("An INFO Message");
        return createAndMatchService.getCreateAndMatches();
    }
//todo *UUUUUUUUUUUUUUUUUUUUUUUUU
    @PostMapping("createAndMatch")
    public CreateAndMatch addCreateAndMatch(@PathVariable int applicantId,@PathVariable int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException {
        logger.info("Add Create And Match");
        return createAndMatchService.addCreateAndMatch(applicantId,jobOfferId);
    }

//    @GetMapping("createAndMatch/{createAndMatchId}")
//    public CreateAndMatch getCreateAndMatchById(@PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
//        return createAndMatchService.getCreateAndMatchById(createAndMatchId);
//    }

    @PutMapping("createAndMatch/{createAndMatchId}")
    public CreateAndMatch updateCreateAndMatch(@RequestBody CreateAndMatch createAndMatch, @PathVariable int createAndMatchId) throws CreateAndMatchNotFound {
        return createAndMatchService.updateCreateAndMatch(createAndMatch,createAndMatchId);
    }
}
