package gr.codehub.chgroupProject.controller.matcherController;

import gr.codehub.chgroupProject.exception.ApplicantNotFoundException;
import gr.codehub.chgroupProject.exception.CreateAndMatchNotFound;
import gr.codehub.chgroupProject.exception.JobOfferNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.model.CreateAndMatch;
import gr.codehub.chgroupProject.service.matcher.AutomaticMatchServiceImpl;
import gr.codehub.chgroupProject.service.matcher.FinalizeServiceImpl;
import gr.codehub.chgroupProject.service.matcher.ManualMatchService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class AutomaticMatchAndFinalizeController {

    Logger logger = LoggerFactory.getLogger(AutomaticMatchAndFinalizeController.class);

    @Autowired
    private ManualMatchService manualMatchService;
    @Autowired
    private AutomaticMatchServiceImpl automaticMatchServiceImpl;
    @Autowired
    private FinalizeServiceImpl finalizeServiceImpl;


    /**
     * Do automatic match
     *
     * @param partial
     * @return
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     * @throws SkillNotFoundException
     */
    @PostMapping("matching")
    public List<CreateAndMatch> doMatch(@RequestParam(required = false) boolean partial) throws ApplicantNotFoundException, JobOfferNotFoundException, SkillNotFoundException {
        logger.info("Create an automic match");
        return automaticMatchServiceImpl.doAutomaticMatch(partial);
    }

    /**
     * Finalize a specific match
     *
     * @param createAndMatchId
     * @return
     * @throws CreateAndMatchNotFound
     * @throws ApplicantNotFoundException
     * @throws JobOfferNotFoundException
     */
    @PostMapping("finalize/{createAndMatchId}")
    public CreateAndMatch finalizeMatch(@PathVariable int createAndMatchId) throws CreateAndMatchNotFound, ApplicantNotFoundException, JobOfferNotFoundException {
        logger.info("Finalize a specific match");
        return finalizeServiceImpl.doFinalize(manualMatchService.findCreateAndMatch(createAndMatchId));
    }

    /**
     * a list of all finalized lists
     *
     * @return a list of all finalized lists
     */
    @GetMapping("finalize")
    public List<CreateAndMatch> finalizedList() {
        logger.info("finalized List");
        return finalizeServiceImpl.finalizedList();
    }

}
