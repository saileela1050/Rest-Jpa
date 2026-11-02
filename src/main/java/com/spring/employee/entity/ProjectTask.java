package com.spring.employee.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="projecttasks")
@Data
@NoArgsConstructor
public class ProjectTask {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

//    @OneToMany
//    @JoinColumn(name="employee_id",nullable = false)
//    private Department department;
}
