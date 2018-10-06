package com.example.springdatajpabasics.repository;

import com.example.springdatajpabasics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by mtumilowicz on 2018-10-03.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    Optional<Employee> findTop1ByName(String name);
    List<Employee> findByIssues_Description(String description);
    List<Employee> findByAddress_City(String city);
    List<Employee> findDistinctEmployeeByNameIgnoreCase(String name);
}
