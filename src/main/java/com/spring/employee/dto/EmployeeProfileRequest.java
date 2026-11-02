package com.spring.employee.dto;

import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeProfileRequest {
    private Employee employee;
    private EmployeeProfile employeeProfile;


}