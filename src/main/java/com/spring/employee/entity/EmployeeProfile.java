package com.spring.employee.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee_profile")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;


    //@OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "employee_id",unique = true,nullable = false)
    private Employee employee;
}
