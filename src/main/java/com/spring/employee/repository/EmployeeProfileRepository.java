package com.spring.employee.repository;


import com.spring.employee.entity.Employee;
import com.spring.employee.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile,Long> {

   Optional<EmployeeProfile> findEmployeesById(Long employeeId);
}
