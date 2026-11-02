package com.spring.employee.controller;


import com.spring.employee.entity.EmployeeProfile;
import com.spring.employee.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee-profiles")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping("{employeeId}")
    public EmployeeProfile createProfile(@PathVariable Long employeeId, @RequestBody EmployeeProfile employeeProfile){
        return employeeProfileService.createProfile(employeeId,employeeProfile);
    }

    @GetMapping("{employeeId}")
    public EmployeeProfile getProfile(@PathVariable Long employeeId){
        return employeeProfileService.getProfile(employeeId);
    }

}
