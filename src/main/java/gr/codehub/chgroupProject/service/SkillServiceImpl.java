package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotValidFields;
import gr.codehub.chgroupProject.model.Skill;
import gr.codehub.chgroupProject.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepo;

    @Override
    public List<Skill> getSkills() {
        return skillRepo.findAll();
    }

    @Override
    public Skill addSkill(Skill skill) throws SkillNotFoundException, SkillNotValidFields {
         if(skill == null){
          throw new SkillNotFoundException("null skill");
         }
        if(  skill.getNameOfSkill().equals("") || skill.getNameOfSkill()==null){ //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
            throw new SkillNotValidFields("Skill fields must not be null");
        }
        return skillRepo.save(skill);
    }

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

    @Override
    public boolean deleteSkill(int skillId) {
        skillRepo.deleteById(skillId);
        return true;
    }

    @Override
    public Skill getSkillById(int skillId) throws SkillNotFoundException {
        Optional<Skill> oSkill = skillRepo.findById(skillId);
        if (oSkill.isPresent()) {
            return oSkill.get();
        }
        else throw new SkillNotFoundException("Job Offer Not Found");


    }

    @Override
    public Skill findSkillByName(String skillName) throws SkillNotFoundException {

        Optional<Skill> oSkill= skillRepo.findSkillByName(skillName);
        if (oSkill.isPresent()){ //ean uparxei epistrefei to jobOffer
            return oSkill.get();
        }
        else throw new SkillNotFoundException("Skill with this name Not Found");
    }
    }


