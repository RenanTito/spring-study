package br.com.project.springossproject.controller;

import java.util.HashMap;
import java.util.Map;
import br.com.project.springossproject.model.User;
//import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
	Map<String, User> users = new HashMap<String, User>();
	
	public RegisterController() {
		User user = new User("Renan", "password", "renan@email.com");
		users.put("Renan", user);
	}
	
	@RequestMapping(value="/users/register", method = RequestMethod.POST)
	@ResponseBody
	public String registerUser(@ModelAttribute("userid") String userid, @ModelAttribute("password") String password, @ModelAttribute("email") String email ) {
		User user = new User(userid, password, email);
		users.put(userid, user);
		return "<html><body bgcolor='coral'>Registered Successfully"
		.concat("<a href='http://localhost:7070/index.html'>Go to Login Page</a>")
		.concat("</body></html>");
	}
	
	@RequestMapping(value="/users/all", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, User> getAllRegisteredUsers(){
		return users;
	}
	
	@RequestMapping(value="/users/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("userid")String userid ) {
		return users.get(userid);
	}
	
	@RequestMapping(value="/users/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("userid")String userid, @ModelAttribute("password")String password, HttpServletRequest request) {
		User user = users.get(userid);
		request.getSession().setAttribute("user", user);
		
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return "contentTablePage";
			}
		} else {
			return "passwordErrorPage";
		}
		return "unrecognizedUserErrorPage";
	}
}
