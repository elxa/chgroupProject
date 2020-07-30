package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.controller.Reporter;
import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SkillServiceImpl implements SkillService {

    Logger logger = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private SkillRepository skillRepo;

    @Override
    public List<Skill> getSkills(String skillName) throws SkillNotFoundException {

        List<Skill> skills = new ArrayList<>();

        if (skillName != null) {
            Optional<Skill> oSkill = skillRepo.findSkillByName(skillName);
            if (oSkill.isPresent()) { //ean uparxei epistrefei to jobOffer
                skills.add(oSkill.get());
                logger.info("Return a skill by name");
                return skills;
            } else throw new SkillNotFoundException("Skill with this name Not Found");
        }
        logger.info("Return a list of skills");
        return skillRepo.findAll();
    }

    @Override
    public Skill addSkill(Skill skill) throws SkillNotFoundException, SkillNotValidFields {
        logger.info("Add a skill in db");
        if (skill == null) {
            throw new SkillNotFoundException("null skill");
        }
        if (skill.getNameOfSkill().equals("") || skill.getNameOfSkill() == null) { //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
            throw new SkillNotValidFields("Skill fields must not be null");
        }
        return skillRepo.save(skill);
    }


   // todo ****************************************************************
    @Override
    public Skill updateSkill(Skill skill, int skillId) throws SkillNotFoundException {
        Skill skillInDb = skillRepo.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("skill not found")
                );
        //se periptwsh pou den brei to id
        skillInDb.setNameOfSkill(skill.getNameOfSkill());

        skillRepo.save(skillInDb);
        return skillInDb;
    }

    //todo na tsekaroume an xtupaei la8os se periptwsh pou den uparxei to exception
    @Override
    public boolean deleteSkill(int skillId) {
        skillRepo.deleteById(skillId);
        return true;
    }

    //todo se enan controller
    @Override
    public Skill getSkillById(int skillId) throws SkillNotFoundException {
        Optional<Skill> oSkill = skillRepo.findById(skillId);
        if (oSkill.isPresent()) {
            return oSkill.get();
        } else throw new SkillNotFoundException("Job Offer Not Found");
    }

    @Override
    public Skill findSkillByName(String skillName) throws SkillNotFoundException {
        Optional<Skill> oSkill = skillRepo.findSkillByName(skillName);
        if (oSkill.isPresent()) { //ean uparxei epistrefei to jobOffer
            return oSkill.get();
        }
        else throw new SkillNotFoundException("Skill with this name Not Found");
    }
    }


