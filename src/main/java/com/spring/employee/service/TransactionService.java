package com.spring.employee.service;

import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import com.spring.employee.repository.EmployeeProfileRepository;
import com.spring.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    public TransactionService(EmployeeRepository employeeRepository, EmployeeProfileRepository employeeProfileRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeProfileRepository = employeeProfileRepository;
    }

   @Transactional
    public Employee createEmployeeProfile(Employee employee, EmployeeProfile employeeProfile) {
        Employee savedEmployee=employeeRepository.save(employee);
        employeeProfile.setEmployee(savedEmployee);
        employeeProfileRepository.save(employeeProfile);

        if("fail".equalsIgnoreCase(employee.getName())){
            throw new RuntimeException("transaction failure occurs");
        }
        return savedEmployee;
    }
}
