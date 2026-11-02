package com.spring.employee.controller;


import com.spring.employee.entity.Employee;
import com.spring.employee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeWithDepartment(employeeId);
    }


    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

//    @GetMapping("{id}")
//    public Employee getEmployeeById(@PathVariable Long id){
//        return employeeService.getEmployeeById(id);
//    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "employee deleted successfully";
    }

// jpql querie controllers

    @GetMapping("/search/name")
    public List<Employee> findByName(@RequestParam String name){
        return employeeService.findEmployeeByName(name);
    }

    @GetMapping("/search/salary")
    public List<Employee> findBySalary(@RequestParam double salary){
        return employeeService.findBySalary(salary);
    }

    @GetMapping("/search/email")
    public List<Employee> findByEmail(@RequestParam String email){
        return employeeService.findByEmail(email);
    }

    @GetMapping("/search/salary-range")
    public List<Employee> findSalaryRange(@RequestParam double min,double max){
        return employeeService.findSalaryRange(min,max);
    }


// sql query controllers

    @GetMapping("/native/salary")
    public List<Employee> findBySalaryGreaterThanNative(@RequestParam double salary) {
        return employeeService.findBySalaryGreaterThanNative(salary);
    }

    @GetMapping("/native/email")
    public List<Employee> findByEmailNative(@RequestParam String email){
        return employeeService.findByEmailThanNative(email);
    }

    @GetMapping("/native/top-salaries")
    public List<Employee> findTop3SalariesEmployeesNative(){
        return employeeService.findTop3SalariesEmployeesNativeThanNative();
    }


    //pagination
    @GetMapping("/page")
    public Page<Employee> getEmployeesWithPagination(@RequestParam (defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size){
        return employeeService.getEmployeesWithPagination(page,size);
    }
//sorting
    @GetMapping("/sort")
    public List<Employee> getEmployeesSorted(@RequestParam(defaultValue = "name") String field,
                                             @RequestParam(defaultValue = "asc") String direction){
        return employeeService.findEmployeesSorted(field, direction);
    }
    //pagination and sorting

    @GetMapping("/page-sort")
    public Page<Employee> getEmployeesWithPaginationAndSorted(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size,
                                             @RequestParam(defaultValue = "name") String field,
                                             @RequestParam(defaultValue = "asc") String direction){
        return employeeService.getEmployeesWithPaginationAndSorted(page,size,field, direction);
    }

    //dynamci jpa filterting with specification

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam (required = false) String name,
                                           @RequestParam(required = false) Double salary,
                                          @RequestParam(required = false) String email
                                          ){
        return employeeService.searchEmployees(name,salary,email);
    }
}
