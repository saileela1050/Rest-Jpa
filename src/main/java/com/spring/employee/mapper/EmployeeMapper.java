package com.spring.employee.mapper;


import com.spring.employee.dto.EmployeeRequest;
import com.spring.employee.dto.EmployeeResponse;
import com.spring.employee.entity.Employee;
import com.spring.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
 public Employee toEntity(EmployeeRequest employeeRequest){
     Employee employee= new Employee();
     employee.setName(employeeRequest.getName());
     employee.setEmail(employeeRequest.getEmail());
     employee.setSalary(employeeRequest.getSalary());
     employee.setJoiningDate(employeeRequest.getJoiningDate());

     return employee;
 }

 public EmployeeResponse toDto(Employee employee)

 {
     EmployeeResponse employeeResponse=new EmployeeResponse();
     employeeResponse.setId(employee.getId());
     employeeResponse.setName(employee.getName());
     employeeResponse.setEmail(employee.getEmail());
     employeeResponse.setJoiningDate(employee.getJoiningDate());

     if (employee.getDepartment() != null) {
         employeeResponse.setDepartmentName(employee.getDepartment().getName());
     }
     employeeResponse.setCreatedDate(employee.getCreatedDate());
     employeeResponse.setUpdatedDate(employee.getUpdatedDate());


     return employeeResponse;
 }}
