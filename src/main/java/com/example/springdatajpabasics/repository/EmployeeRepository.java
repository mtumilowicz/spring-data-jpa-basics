package com.example.springdatajpabasics.repository;

import com.example.springdatajpabasics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mtumilowicz on 2018-10-03.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
