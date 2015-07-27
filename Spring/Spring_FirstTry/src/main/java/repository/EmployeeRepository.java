package main.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByName(String name);

}
