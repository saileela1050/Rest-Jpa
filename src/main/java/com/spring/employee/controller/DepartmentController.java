package com.spring.employee.controller;


import com.spring.employee.entity.Department;
import com.spring.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

//    @PutMapping("{id}")
//    public Department updateEmployees(@PathVariable Long id, @RequestBody Department department){
//
//        return departmentService.updateEmployee(id,department);
//    }
//
//

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id ){
        return departmentService.getDepartmentsById(id);
    }

    //  @GetMapping("/{id}")
    //    public Department getDepartmentById(@PathVariable Long id ){
    //        return departmentService.getDepartmentsById(id);
    //    }
}
