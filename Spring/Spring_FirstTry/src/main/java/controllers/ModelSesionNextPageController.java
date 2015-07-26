package main.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class ModelSesionNextPageController {

	@RequestMapping("/diffnext")
	public String requestHandlingMethodNext(SessionStatus status) {
		// status.setComplete();
		return "ModelSessionDiff";
	}

}