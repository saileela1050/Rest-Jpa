package com.spring.employee.service;

import com.spring.employee.entity.Employee;
import com.spring.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptimisticLockService {

    private final EmployeeRepository employeeRepository;

    public OptimisticLockService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

   @Transactional
    public Employee updateEmployee(Long id, Double salary) {

        Employee employee=employeeRepository.findById(id).orElseThrow(()->
                new RuntimeException("employee not found"));
        employee.setSalary(salary);
        try{
            return employeeRepository.saveAndFlush(employee);
        } catch (ObjectOptimisticLockingFailureException exception) {
            throw exception;
        }
    }

    public Optional<Employee> getEmployeeById(Long id) {
         return employeeRepository.findById(id);

    }

//    public Employee getEmployeeById(Id id) {
//        return employeeRepository.findById(id);
//    }
}
