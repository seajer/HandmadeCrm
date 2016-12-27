package ua.lviv.calltech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@RequestMapping(value = "/new_user", method = RequestMethod.GET)
	public String newUser(){
		return "users-new";
	}
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestParam("name")String name, @RequestParam("email")String email,
			@RequestParam("phone")String phone, @RequestParam("password")String password){
		System.out.println("name = " + name);
		System.out.println("email = " + email);
		System.out.println("phone = " + phone);
		System.out.println("pass = " + password);
		return "users-new";
	}
}
