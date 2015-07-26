package main.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import main.java.beans.Student;

@Controller
@SessionAttributes("myObj")
public class ModelSessionHomeController {

	@ModelAttribute("myObj")
	public Student addDefaultStudent() {
		return new Student();
	}

	@RequestMapping("/model")
	public String requestHandlingMethodHome() {
		return "ModelSessionHome";
	}

	@RequestMapping("/endsession")
	public String nextHandlingMethod2(SessionStatus status) {
		status.setComplete();
		return "EndSessionPage";
	}

}