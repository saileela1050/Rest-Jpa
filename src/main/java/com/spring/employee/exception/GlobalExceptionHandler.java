package com.spring.employee.exception;

import jakarta.persistence.ManyToOne;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String,String>> handleDuplicateEmail(DuplicateEmailException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error",exception.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> handleDataIntegrityViolation(DataIntegrityViolationException dataIntegrityViolationException){
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(Map.of("error","a database constraint was violated .check for duplicate email"));
    }

    @ExceptionHandler({OptimisticLockingFailureException.class, ObjectOptimisticLockingFailureException.class})
    public ResponseEntity<Map<String,String>> handleOptimisticLocking(OptimisticLockingFailureException optimisticLockingFailureException,ObjectOptimisticLockingFailureException objectOptimisticLockingFailureException){
       return ResponseEntity.status(HttpStatus.CONFLICT).
               body(Map.of("error","This employee was modified by another request. Please reload and try again."));
    }

}
