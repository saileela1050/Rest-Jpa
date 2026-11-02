package com.spring.employee.service;

import com.spring.employee.entity.Employee;
import com.spring.employee.entity.Skill;
import com.spring.employee.repository.EmployeeRepository;
import com.spring.employee.repository.SkillRepository;
import com.spring.employee.specification.EmployeeSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {


//    @Autowired
//    private EmployeeRepository employeeRepository;

    private  final EmployeeRepository employeeRepository;

    private final SkillRepository skillRepository;

    public EmployeeService(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Transactional()
    public Employee getEmployeeWithDepartment(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public  Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new
                RuntimeException("employee not found with id:" +id));
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAllWithDepartments();
    }
    public Employee updateEmployee(Long id, Employee employee) {

        Employee employee1=getEmployeeById(id);
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setSalary(employee.getSalary());

        return employeeRepository.save(employee1);
    }



    public void deleteEmployee(Long id) {

        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee not found with id:" +id);
        }
       employeeRepository.deleteById(id);
    }


    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeesByName(name);
    }

    public List<Employee> findBySalary(double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
    }

    public List<Employee> findByEmail(String email) {
        return employeeRepository.findEmployeesByEmail(email);

    }

    public List<Employee> findSalaryRange(double min, double max) {
        return employeeRepository.findEmployeesBySalaryRange(min,max);

    }

    public List<Employee> findBySalaryGreaterThanNative(double salary) {
        return employeeRepository.findBySalaryGreaterThanNative(salary);
    }

    public List<Employee> findByEmailThanNative(String email) {
        return employeeRepository.findEmployeesByEmailThanNative(email);
    }

    public List<Employee> findTop3SalariesEmployeesNativeThanNative() {
        return employeeRepository.findEmployeesTop3HighestPaid();
    }


    public Page<Employee> getEmployeesWithPagination(int page, int size) {

        Pageable pageable= PageRequest.of(page,size);
        return employeeRepository.findAll(pageable);
    }
    public List<Employee> findEmployeesSorted(String field, String direction) {

        Sort sort=direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return employeeRepository.findAll(sort);
    }


    public Page<Employee> getEmployeesWithPaginationAndSorted(int page, int size, String field, String direction) {

    Sort sort=direction.equalsIgnoreCase("asc")
    ?Sort.by(field).ascending():Sort.by(field).descending();

    Pageable pageable=PageRequest.of(page,size,sort);
    return employeeRepository.findAll(pageable);

    }

    public List<Employee> searchEmployees(String name, Double salary, String email) {

        Specification<Employee> specification=Specification.where(EmployeeSpecification.hasName(name)
                .and(EmployeeSpecification.salaryGreaterThan(salary)
                        .and(EmployeeSpecification.hasEmail(email))));
        return employeeRepository.findAll(specification);
    }

    public Employee updateSkill(Long employeeId, Set<Long> skillIds) {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->
                new RuntimeException("Employee not found with id:" +employeeId));

        Set<Skill> skills=new HashSet<>(skillRepository.findAllById(skillIds));
        if(skills.size()!= skillIds.size()){
            throw new RuntimeException("one or more skills not found");
        }
       employee.setSkills(skills);
        return employeeRepository.save(employee);


    }

}
