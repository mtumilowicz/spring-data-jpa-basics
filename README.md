[![Build Status](https://travis-ci.com/mtumilowicz/spring-data-jpa-basics.svg?token=PwyvjePQ7aiAX51hSYLE&branch=master)](https://travis-ci.com/mtumilowicz/spring-data-jpa-basics)

# spring-data-jpa-basics
In this project we cover basic features of Spring Data JPA.

_Reference_: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

# feature covered
* Spring Data Repositories (`JpaRepository`)
* Custom select query methods:
    * `List<Employee> findByName(String name);`
    * `Optional<Employee> findTop1ByName(String name);`
    * `List<Employee> findByIssues_Description(String description);`
    * `List<Employee> findByAddress_City(String city);`
    * `List<Employee> findDistinctEmployeeByNameIgnoreCase(String name);`
* Repository select query methods (shown in `SpringDataBasicsTest`):
    * paging: `repository.findAll(PageRequest.of(2, 2));`
    * sorting: `repository.findAll(Sort.by("name").ascending());`
    * paging + sorting: 
        ```
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("address.city").ascending());
        Page<Employee> employee = repository.findAll(pageRequest);
        ```
        
# tests
All features listed above are tested in: `SpringDataBasicsTest`