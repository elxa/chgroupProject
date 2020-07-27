package gr.codehub.chgroupProject.service;

import gr.codehub.chgroupProject.excheption.JobOfferNotFoundException;
import gr.codehub.chgroupProject.excheption.SkillNotFoundException;
import gr.codehub.chgroupProject.model.JobOffer;
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
    public Skill addSkill(Skill skill) {
        // if(skill == null){
        //   throw new CustomerCreationException("null job Offer");
        // }
//        if( customer.getEmail()==null || !customer.getEmail().contains("@")){ //ean den balei email xtupaei null pointer excheption paizei rolo h seira edw
//            throw new CustomerCreationException("invalid ffff");
//        }
        return skillRepo.save(skill);
    }

    @Override
    public Skill updateSkill(Skill skill, int skillId) {
        Skill skillInDb = skillRepo.findById(skillId).get();
//                .orElseThrow("sss"
//                     //   () -> new CustomerNotFoundException("not such customer")
//                              );
        //se periptwsh pou den brei to id
        skillInDb.setNameOfSkill(skill.getNameOfSkill());
        skillInDb.setLevelOfSkill(skill.getLevelOfSkill());


        skillRepo.save(skillInDb);
        return skillInDb;
    }

    @Override
    public boolean deleteSkill(int skillId) {
        skillRepo.deleteById(skillId);
        return true;
    }

    @Override
    public Skill getSkillById(int skillId) {
        Optional<Skill> oSkill = skillRepo.findById(skillId);
        if (oSkill.isPresent()) {
            return oSkill.get();
        }
        //else throw new JobOfferNotFoundException("Job Offer Not Found");
        // }
        return oSkill.get();//todo einai gia dior8wsh prepei na sbhstei
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


