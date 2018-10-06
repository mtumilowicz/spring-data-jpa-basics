package com.example.springdatajpabasics;

import com.example.springdatajpabasics.entity.Employee;
import com.example.springdatajpabasics.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-10-05.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDataBasicsTest {
    
    @Autowired
    EmployeeRepository repository;
    
    
    @Test
    public void paging() {
        Page<Employee> all = repository.findAll(PageRequest.of(2, 2));
        
        assertThat(all.getTotalElements(), is(4));
        assertThat(all.getTotalPages(), is(2));
    }

    @Test
    public void sorting() {
        List<Employee> employees = repository.findAll(Sort.by("name").ascending());
    }

    @Test
    public void pagingAndSorting() {
        Page<Employee> name = repository.findAll(PageRequest.of(2, 2, Sort.by("name").ascending()));
    }
}
