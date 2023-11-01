package br.com.project.springmicroserviceproject.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PureTextController {
	
	@RequestMapping("/") 
	public String index() { 
		return "Greetings from Spring Boot - Mr Title !"; 
	}

}
