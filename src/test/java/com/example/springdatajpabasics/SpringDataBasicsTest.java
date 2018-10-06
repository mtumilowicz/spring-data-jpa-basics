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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

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
        Page<Employee> pages = repository.findAll(PageRequest.of(2, 2));

        assertThat(pages.getTotalElements(), is(4L));
        assertThat(pages.getTotalPages(), is(2));
    }

    @Test
    public void sorting() {
        List<Employee> employees = repository.findAll(Sort.by("name").ascending());

        assertThat(employees, hasSize(4));

        Employee employee1 = employees.get(0);
        Employee employee2 = employees.get(1);
        Employee employee3 = employees.get(2);
        Employee employee4 = employees.get(3);

        assertThat(employee1.getName(), is("Hemingway"));
        assertThat(employee2.getName(), is("Lem"));
        assertThat(employee3.getName(), is("Mrozek"));
        assertThat(employee4.getName(), is("Tumilowicz"));

    }

    @Test
    public void pagingAndSorting() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("address.city").ascending());
        Page<Employee> employee = repository.findAll(pageRequest);


        assertThat(employee.getContent().get(0).getAddress().getCity(), is("Key West"));
    }

    @Test
    public void findByName() {
        List<Employee> hemingways = repository.findByName("Hemingway");

        assertThat(hemingways, hasSize(1));
        assertThat(hemingways.get(0).getName(), is("Hemingway"));
    }

    @Test
    public void findTop1ByName() {
        Optional<Employee> top1Hemingway = repository.findTop1ByName("Hemingway");

        assertTrue(top1Hemingway.isPresent());
        assertThat(top1Hemingway.get().getName(), is("Hemingway"));
    }

    @Test
    public void findByIssues_Description() {
        List<Employee> employees = repository.findByIssues_Description("improve coding skills");

        assertThat(employees, hasSize(1));
        assertThat(employees.get(0).getName(), is("Tumilowicz"));
    }

    @Test
    public void findByAddress_City() {
        List<String> employeeNames = repository.findByAddress_City("Krakow")
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        assertThat(employeeNames, hasSize(2));
        assertThat(employeeNames, contains("Mrozek", "Lem"));
    }

    @Test
    public void findDistinctEmployeeByNameIgnoreCase() {
        List<Employee> employees = repository.findDistinctEmployeeByNameIgnoreCase("MROZEK");

        assertThat(employees, hasSize(1));
        assertThat(employees.get(0).getName(), is("Mrozek"));
    }
}
