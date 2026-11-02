package com.spring.employee.controller;


import com.spring.employee.entity.Employee;
import com.spring.employee.service.OptimisticLockService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/optimistic")
public class OptimisticLockController {

    private final OptimisticLockService optimisticLockService;

    public OptimisticLockController(OptimisticLockService optimisticLockService) {
        this.optimisticLockService = optimisticLockService;
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestParam Double salary){
        return optimisticLockService.updateEmployee(id,salary);
    }

    @GetMapping("{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id){
        return optimisticLockService.getEmployeeById(id);
    }
}
