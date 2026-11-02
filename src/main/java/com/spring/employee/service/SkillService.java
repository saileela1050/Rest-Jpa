package com.spring.employee.service;

import com.spring.employee.entity.Skill;
import com.spring.employee.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);

    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

//    public Skill updateSkill(Long id, Skill skill) {
//
//        Skill skill1= getAllSkills(id);
//        skill1.setName(skill.getName());
//        skill1.setPhonenumber(skill.getPhonenumber());
//        return skillRepository.save(skill1);
//
//    }
}
