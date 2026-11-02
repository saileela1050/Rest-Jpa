package com.spring.employee.service;


import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import com.spring.employee.repository.EmployeeProfileRepository;
import com.spring.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RollbackService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    public RollbackService(EmployeeRepository employeeRepository, EmployeeProfileRepository employeeProfileRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @Transactional(rollbackFor=Exception.class)
    public void createRollback(Employee employee, EmployeeProfile employeeProfile) throws Exception {
        Employee employee1=employeeRepository.save(employee);
        employeeProfile.setEmployee(employee1);
        employeeProfileRepository.save(employeeProfile);

        throw new Exception("Something went wrong!");
    }
}
