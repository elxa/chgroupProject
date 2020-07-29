package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.exception.SkillNotFoundException;
import gr.codehub.chgroupProject.exception.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepo;


    /** Wr create this method in order to get all skills
     * @param skillName
     * @return a list with all skills of the repo
     * @throws SkillNotFoundException
     */
    @Override
    public List<Skill> getSkills(String skillName) throws SkillNotFoundException {

        List<Skill> skills = new ArrayList<>();

        if (skillName != null) {
            Optional<Skill> oSkill = skillRepo.findSkillByName(skillName);
            if (oSkill.isPresent()) { //ean uparxei epistrefei to jobOffer
                skills.add(oSkill.get());
                return skills;
            } else throw new SkillNotFoundException("Skill with this name Not Found");
        }
        return skillRepo.findAll();
    }

    /**
     * We create this method in order to create a new skill
     * @param skill
     * @return a list with all new saved skills
     * @throws SkillNotFoundException
     * @throws SkillNotValidFields
     */

    @Override
    public Skill addSkill(Skill skill) throws SkillNotFoundException, SkillNotValidFields {
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
     * @param skill
     * @param skillId
     * @return skillInDb
     * @throws SkillNotFoundException
     */

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

    /**
     * We create this method in order to be able to delete an existing skill
     * @param skillId
     * @return true
     */
    public boolean deleteSkill(int skillId) {
        skillRepo.deleteById(skillId);
        return true;
    }

    /**
     * We create this method in order to get a specific skill according to its id
     * @param skillId
     * @return the skill with the specific Id
     * @throws SkillNotFoundException
     */
    @Override
    public Skill getSkillById(int skillId) throws SkillNotFoundException {
        Optional<Skill> oSkill = skillRepo.findById(skillId);
        if (oSkill.isPresent()) {
            return oSkill.get();
        } else throw new SkillNotFoundException("Job Offer Not Found");
    }

    /**
     * We create this method in order to get a specific skill according to their name
     * @param skillName
     * @return the skill with a certain name
     * @throws SkillNotFoundException
     */

    @Override
    public Skill findSkillByName(String skillName) throws SkillNotFoundException {
        Optional<Skill> oSkill = skillRepo.findSkillByName(skillName);
        if (oSkill.isPresent()) { //ean uparxei epistrefei to jobOffer
            return oSkill.get();
        }
        else throw new SkillNotFoundException("Skill with this name Not Found");
    }
    }


