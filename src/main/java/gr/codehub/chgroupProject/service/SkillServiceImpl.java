package gr.codehub.chgroupProject.service;

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

    /**
     * A list of skills
     *
     * @return a list of skills
     */
    @Override
    public List<Skill> getAllSkills() {
        logger.info("Return a list of skills");
        return skillRepo.findAll();
    }

    //todo na to 3anadw

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

    /**
     * Add a skill in db
     *
     * @param skill
     * @return
     * @throws SkillNotFoundException
     * @throws SkillNotValidFields
     */
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

    /**
     * We create this method in order to be able to update a skill
     *
     * @param skill
     * @param skillId
     * @return skillInDb
     * @throws SkillNotFoundException
     */
    @Override
    public Skill updateSkill(Skill skill, int skillId) throws SkillNotFoundException {
        logger.info("Update skill in db");
        Skill skillInDb = skillRepo.findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("skill not found")
                );
        //se periptwsh pou den brei to id
        skillInDb.setNameOfSkill(skill.getNameOfSkill());

        skillRepo.save(skillInDb);
        return skillInDb;
    }

    /**
     * We create this method in order to be able to delete an existing skill
     *
     * @param skillId
     * @return true
     */
    @Override
    public boolean deleteSkill(int skillId) throws SkillNotFoundException {
        logger.info("Delete a skill in db");
        Optional<Skill> oSkill = skillRepo.findById(skillId);
        if (oSkill.isPresent()) { //ean uparxei epistrefei to jobOffer
            skillRepo.deleteById(skillId);
            return true;
        } else throw new SkillNotFoundException("Skill Not Found");
    }
    /**
     * find skill by id
     *
     * @param skillId
     * @return
     * @throws SkillNotFoundException
     */
    @Override
    public Skill getSkillById(int skillId) throws SkillNotFoundException {
        logger.info("Get skill by id");
        Optional<Skill> oSkill = skillRepo.findById(skillId);
        if (oSkill.isPresent()) {
            return oSkill.get();
        } else throw new SkillNotFoundException("Skill Not Found");
    }

    /**
     * find a skill by name
     *
     * @param skillName
     * @return
     * @throws SkillNotFoundException
     */
    @Override
    public Skill findSkillByName(String skillName) throws SkillNotFoundException {
        logger.info("Get a skill by name");
        Optional<Skill> oSkill = skillRepo.findSkillByName(skillName);
        if (oSkill.isPresent()) { //ean uparxei epistrefei to jobOffer
            return oSkill.get();
        } else throw new SkillNotFoundException("Skill with this name Not Found");
    }
}
