package main.java.controllers;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/reg")
public class RegisterController {

	 @RequestMapping(value = "/requestbody", method = RequestMethod.POST)
	  public void writeRequestBody(@RequestBody String body, Writer writer) throws IOException {

	    // This will be the contents of the next page you see
	    writer.write(body);
	  }

}
