package com.spring.employee.dto;

import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RollbackRequest {

    private Employee employee;
    private EmployeeProfile employeeProfile;
}
