package com.spring.employee.service;

import com.spring.employee.entity.Employee;
import com.spring.employee.exception.DuplicateEmailException;
import com.spring.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadTranService {

    private final EmployeeRepository employeeRepository;

    public ReadTranService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
     return employeeRepository.findById(id).orElseThrow(()->
                new RuntimeException("Employee not found:" +id));

    }
   @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        if(employeeRepository.existsByEmail(employee.getEmail())){
            throw new DuplicateEmailException("Employee email already exists: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}
