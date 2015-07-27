package main.java.service;

import java.util.List;

import main.java.entity.Employee;

public interface EmployeeService {

	Employee add(Employee emp);
	void delete(long id);
	Employee getByName(String name);
	Employee edit(Employee emp);
	List<Employee> getAll();
	
}
