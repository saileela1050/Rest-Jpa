package com.spring.employee.controller;

import com.spring.employee.dto.EmployeeRequest;
import com.spring.employee.dto.EmployeeResponse;
import com.spring.employee.entity.Employee;
import com.spring.employee.service.EmployeeDtoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dto")
public class EmployeeDtoController {


    private final EmployeeDtoService employeeDtoService;

    public EmployeeDtoController(EmployeeDtoService employeeDtoService) {
        this.employeeDtoService = employeeDtoService;
    }

    @PostMapping
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeDtoService.createEmployeeFromDto(employeeRequest);
    }

    @GetMapping("{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id){
        return employeeDtoService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees(){
        return employeeDtoService.getAllEmployees();
    }
    @PutMapping("{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest){
        return employeeDtoService.updateEmployee(id,employeeRequest);
    }
}
