package main.java.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HitController {

	@RequestMapping(value = "/hit")
	public String hello(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,
			HttpServletResponse response) {

		hitCounter++;

		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
		// lifetime=10min (-1 delete on browser close; 0 - delete)
		cookie.setMaxAge(600);
		response.addCookie(cookie);

		return "HitCounter";
	}

}