package com.spring.employee.repository;

import com.spring.employee.entity.Employee;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {


    //jpql queries

    @Query("SELECT e FROM Employee e WHERE e.name=:name")
    List<Employee> findEmployeesByName(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.salary>:salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") double salary);

    @Query("SELECT e FROM Employee e WHERE e.email=:email")
    List<Employee> findEmployeesByEmail(@Param("email") String email);

    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :min AND :max")
    List<Employee> findEmployeesBySalaryRange(@Param("min") double min,@Param("max") double max);

    //sql queries

    @Query(value="SELECT * FROM employee WHERE salary>:salary",nativeQuery = true)
    List<Employee> findBySalaryGreaterThanNative(@Param("salary") double salary);

    @Query(value = "SELECT * FROM employee WHERE email=:email",nativeQuery = true)
    List<Employee> findEmployeesByEmailThanNative(@Param("email")String email);

    @Query(value = "SELECT * FROM employee ORDER BY salary desc LIMIT 3",nativeQuery = true)
    List<Employee> findEmployeesTop3HighestPaid();

    List<Employee> findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);


    @EntityGraph(attributePaths = {"department"})
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllWithDepartments();



//    @EntityGraph(attributePaths = {"dep"})
//    @Query("SELECT e FROM EMPLOYEE e")
//    List<Employee> findAllWIthDepartments();
}
