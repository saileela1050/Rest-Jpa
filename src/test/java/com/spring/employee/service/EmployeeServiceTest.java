package com.spring.employee.service;


import com.spring.employee.dto.EmployeeRequest;
import com.spring.employee.dto.EmployeeResponse;
import com.spring.employee.entity.Employee;
import com.spring.employee.mapper.EmployeeMapper;
import com.spring.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeDtoService employeeDtoService;

    @Test
    void createEmployeeTest(){
        EmployeeRequest employeeRequest=new EmployeeRequest();
        employeeRequest.setName("rahul");
        employeeRequest.setEmail("rahul@gmail.com");
        employeeRequest.setSalary(1000.0);

        Employee employee=new Employee();
        employee.setName("ram");
        employee.setEmail("ram@gmail.com");
        employee.setSalary(159.0);

        Employee savedEmployee=new Employee();
        savedEmployee.setId(1L);
        savedEmployee.setName("ram");
        savedEmployee.setEmail("ram@gmail.com");
        savedEmployee.setSalary(159.0);

        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setId(1L);
        employeeResponse.setName("rahul");
        employeeResponse.setEmail("rahul@gmail.com");
        employeeResponse.setSalary(1000.0);

        when(employeeMapper.toEntity(employeeRequest)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);
        when(employeeMapper.toDto(savedEmployee)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeDtoService.createEmployeeFromDto(employeeRequest);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("rahul", result.getName());

        verify(employeeRepository, times(1)).save(employee);



    }
    @Test
    void getEmployeeByIdTest() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Rahul");

        EmployeeResponse response = new EmployeeResponse();
        response.setId(1L);
        response.setName("Rahul");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(employee)).thenReturn(response);
        EmployeeResponse result = employeeDtoService.getEmployeeById(1L);
        assertEquals(1L, result.getId());
        assertEquals("Rahul", result.getName());
        verify(employeeRepository).findById(1L);
    }
    @Test
    void getEmployeeByIdNotFoundTest() {

        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeeDtoService.getEmployeeById(99L));
        assertEquals("Employee not found with id:99", exception.getMessage());
    }
}
