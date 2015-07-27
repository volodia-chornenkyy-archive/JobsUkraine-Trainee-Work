package main.java.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.entity.Employee;
import main.java.service.EmployeeService;

@Controller
@RequestMapping("/employee")
@ComponentScan("main.java.service")
public class EmployeeRegistration {

	@Autowired
	private EmployeeService empServ;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String goToRegistration(Map<String,Object> model) {
		model.put("employeeForm", new Employee());
		return "EmployeeRegistration";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submitRegistration(@ModelAttribute("employeeForm") Employee emp, BindingResult result) {
		System.out.println(emp);
		empServ.add(emp);
	}

}
