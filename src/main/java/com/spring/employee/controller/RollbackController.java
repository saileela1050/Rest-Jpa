package com.spring.employee.controller;

import com.spring.employee.dto.RollbackRequest;
import com.spring.employee.entity.Employee;
import com.spring.employee.service.RollbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rollback")
public class RollbackController {

    private final RollbackService rollbackService;

    public RollbackController(RollbackService rollbackService) {
        this.rollbackService = rollbackService;
    }

    @PostMapping("/test")
    public ResponseEntity<String> createRollback(@RequestBody RollbackRequest rollbackRequest) throws Exception {
       rollbackService.createRollback(rollbackRequest.getEmployee(),rollbackRequest.getEmployeeProfile());
       return ResponseEntity.ok("operation completed");
    }
}
