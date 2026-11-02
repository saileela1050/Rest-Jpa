package com.spring.employee.specification;

import com.spring.employee.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name){
        return (root, query, criteriaBuilder) -> name==null
        || name.isBlank()?null:criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%"+name.toLowerCase()+"%");
    }

    public static Specification<Employee> salaryGreaterThan(Double salary){
        return (root, query, criteriaBuilder) -> salary==null
                ?null:criteriaBuilder.greaterThan(root.get("salary"),salary);
    }

    public static Specification<Employee> hasEmail(String email){
        return (root, query, criteriaBuilder) -> email==null
                || email.isBlank()?null:criteriaBuilder.equal(root.get("email"),email);
    }
}
