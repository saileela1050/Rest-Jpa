package com.spring.employee.controller;


import com.spring.employee.dto.EmployeeProfileRequest;
import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import com.spring.employee.service.TransactionService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

//    @PostMapping("/employee-profile")
//    public Employee createEmployeeProfile(@RequestBody EmployeeProfileRequest request){
//        return transactionService.createEmployeeProfile(request.getEmployee(),request.getEmployeeProfile());
//    }

    @PostMapping("/employee-profile")
    public Employee createEmployeeProfile(@RequestBody EmployeeProfileRequest request) {
        return transactionService.createEmployeeProfile(request.getEmployee(), request.getEmployeeProfile());
    }


    }

