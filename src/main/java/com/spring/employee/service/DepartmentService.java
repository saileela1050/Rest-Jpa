package com.spring.employee.service;


import com.spring.employee.entity.Department;
import com.spring.employee.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Department getDepartmentsById(Long id) {
        return departmentRepository.findById(id).orElseThrow(()->
                new RuntimeException("Employee not found with id :" +id));

    }
//
//    public Department updateEmployee(Long id, Department department) {
//
//        Department department1=getDepartmentsById(id);
//        department1.setName(department.getName());
//        return departmentRepository.save(department1);
//
//    }
}
