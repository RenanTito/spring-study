package br.com.project.springmicroserviceproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageViewController {
	
	@RequestMapping("/hello")
	public String Hello() {
		return "hello";
	}

}
