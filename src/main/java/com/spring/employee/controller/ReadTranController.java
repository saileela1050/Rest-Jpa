package com.spring.employee.controller;

import com.spring.employee.entity.Employee;
import com.spring.employee.service.ReadTranService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readonly")
public class ReadTranController {

    private final ReadTranService readTranService;

    public ReadTranController(ReadTranService readTranService) {
        this.readTranService = readTranService;
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id){
      return readTranService.getEmployeeById(id);

    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return readTranService.getAllEmployees();
    }

    @GetMapping("/search")
    public List<Employee> serachNameContaining(@RequestParam String name){
        return readTranService.getEmployeeByName(name);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return readTranService.createEmployee(employee);
    }
}
