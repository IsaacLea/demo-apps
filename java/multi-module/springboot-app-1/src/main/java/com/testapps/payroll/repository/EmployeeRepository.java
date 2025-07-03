package com.testapps.payroll.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.testapps.payroll.model.Employee;

/**
 * A JPA repository class. Docs: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Example of a custom query method
	@Query("select e from Employee e where e.name = ?1")
	Employee findByNameCustom(String name);

	// Example of a sort. To call this method: repo.findByAndSort("smith", Sort.by("name"));
	@Query("select e from Employee e where e.name like ?1%")
	List<Employee> findByAndSort(String lastname, Sort sort);

	// Example of using named params (by default JSA does position based param binding)
//	@Query("select e from Employee e where u.firstname = :firstname or u.lastname = :lastname")
//	Employee findByLastnameOrFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);

	// Example of a native custom query method with pagination
	@NativeQuery(value = "SELECT * FROM employee WHERE name = ?1", countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1")
	Page<Employee> findByNameCustomWithPagination(String lastname, Pageable pageable);

}
