package com.spring.employee.service;

import com.spring.employee.dto.EmployeeRequest;
import com.spring.employee.dto.EmployeeResponse;
import com.spring.employee.entity.Employee;
import com.spring.employee.mapper.EmployeeMapper;
import com.spring.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeDtoService {


private final EmployeeRepository employeeRepository;
private final EmployeeMapper employeeMapper;

    public EmployeeDtoService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public EmployeeResponse createEmployeeFromDto(@Valid EmployeeRequest employeeRequest) {

        Employee employee=employeeMapper.toEntity(employeeRequest);
        Employee savedEmployee=employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Transactional(readOnly=true)
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->
                new RuntimeException("Employee not found with id:" +id));
        return employeeMapper.toDto(employee);
    }

    @Transactional(readOnly=true)
    public List<EmployeeResponse> getAllEmployees() {
     return employeeRepository.findAllWithDepartments().stream().map(employeeMapper::toDto).toList();

    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
//        Employee employee=employeeRepository.findById(id).orElseThrow(()->
//                new RuntimeException("not found"));

        Employee employee1=employeeMapper.toEntity(employeeRequest);
        employee1.setName(employeeRequest.getName());
        Employee savedEmployee=employeeRepository.save(employee1);
        return employeeMapper.toDto(savedEmployee);

    }
}
