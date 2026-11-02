package com.spring.employee.service;


import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import com.spring.employee.repository.EmployeeProfileRepository;
import com.spring.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProfileService {


    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeProfileService(EmployeeProfileRepository employeeProfileRepository, EmployeeRepository employeeRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
        this.employeeRepository = employeeRepository;
    }


    public EmployeeProfile createProfile(Long employeeId, EmployeeProfile employeeProfile) {
        
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->
                new RuntimeException("Employee not found"));

        if(employeeProfileRepository.findEmployeesById(employeeId).isPresent()){
            throw new RuntimeException("Profile already exists for this employee ");
        }
    //employeeProfile.setEmployee(employee);
        return employeeProfileRepository.save(employeeProfile);
    }

    public EmployeeProfile getProfile(Long employeeId) {
        return employeeProfileRepository.findEmployeesById(employeeId).orElseThrow(()->
                new RuntimeException("Profile not found"));
    }
}
