package com.spring.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @Positive
    private Double salary;
    private LocalDate joiningDate;
}
