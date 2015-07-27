package main.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.entity.Employee;
import main.java.repository.EmployeeRepository;
import main.java.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public Employee add(Employee emp) {
		return empRepo.saveAndFlush(emp);
	}

	@Override
	public void delete(long id) {
		empRepo.delete(id);
	}

	@Override
	public Employee getByName(String name) {
		return empRepo.findByName(name);
	}

	@Override
	public Employee edit(Employee emp) {
		return empRepo.saveAndFlush(emp);
	}

	@Override
	public List<Employee> getAll() {
		return empRepo.findAll();
	}

}
