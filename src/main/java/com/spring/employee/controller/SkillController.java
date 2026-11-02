package com.spring.employee.controller;


import com.spring.employee.entity.Employee;
import com.spring.employee.entity.Skill;
import com.spring.employee.service.EmployeeService;
import com.spring.employee.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;
    private final EmployeeService employeeService;

    public SkillController(SkillService skillService, EmployeeService employeeService) {
        this.skillService = skillService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill){
        return skillService.createSkill(skill);
    }

    @GetMapping
    public List<Skill> getAllSkills(){
        return skillService.getAllSkills();
    }

    @PutMapping("{id}/update-skill")
    public Employee updateSkill(@PathVariable Long employeeId, @RequestBody Set<Long> skillIds){
        return employeeService.updateSkill(employeeId,skillIds);
    }
}
