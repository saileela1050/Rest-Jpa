package com.spring.employee.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmployeeResponse {


    private Long id;
    private String name;
    private String email;
    private Double salary;
    private LocalDate joiningDate;
    private String departmentName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
