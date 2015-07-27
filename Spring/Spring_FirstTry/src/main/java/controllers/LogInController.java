package main.java.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.beans.Student;

@Controller
public class LogInController {

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public String viewLogin(Map<String, Object> model) {
		Student s = new Student();
		model.put("userForm", s);
		return "LogIn";
	}

}