package com.spring.employee.service;


import com.spring.employee.entity.Employee;
import com.spring.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSoftDeleteService {

    private final EmployeeRepository employeeRepository;

    public EmployeeSoftDeleteService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id).orElseThrow(()->
                new RuntimeException("not found"));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(()->
                new RuntimeException("not found"));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }
}
