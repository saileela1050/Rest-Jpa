package com.spring.employee.controller;


import com.spring.employee.dto.EmployeeResponse;
import com.spring.employee.entity.Employee;
import com.spring.employee.service.EmployeeSoftDeleteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/softdelete")
public class EmployeeSoftDeleteController {

    private final EmployeeSoftDeleteService employeeSoftDeleteService;

    public EmployeeSoftDeleteController(EmployeeSoftDeleteService employeeSoftDeleteService) {
        this.employeeSoftDeleteService = employeeSoftDeleteService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeSoftDeleteService.getEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeSoftDeleteService.getEmployeeById(id);
    }

    @DeleteMapping("{id}")
    public String deleteEmployeeById(@PathVariable Long id){
         employeeSoftDeleteService.deleteEmployee(id);
        return "deleted successfully";
    }
}


