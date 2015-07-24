package main.java.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.beans.Student;

@Controller
public class UserPageController {

//	@RequestMapping(value = "/user", method = RequestMethod.GET)
//	public String init() {
//		return "UserPage";
//	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("userForm") Student userForm, BindingResult result, Map<String, Object> model,
			Model model2) {

		if (result.hasErrors()) {
			return "LogIn";
		}

		model2.addAttribute("userForm", userForm);

		return "UserPage";
	}

}
