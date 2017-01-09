package ua.lviv.calltech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.service.RoleService;
import ua.lviv.calltech.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/new_user", method = RequestMethod.GET)
	public String newUser(Model model){
		if(roleService.findAll().size()<1){
			roleService.saveRoles();
		}
		model.addAttribute("roles", roleService.findAll());
		return "users-new";
	}
	
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestParam("name")String name, @RequestParam("email")String email,
			@RequestParam("phone")String phone, @RequestParam("password")String password, @RequestParam("role")int roleId){
		userService.addUser(name, email, phone, password, roleId);
		return "redirect:/all_users";
	}
	
	@RequestMapping(value="/delete_user_{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id){
		userService.deleteUser(id);
		return "redirect:/all_users";
	}
	
	@RequestMapping(value="/all_users", method = RequestMethod.GET)
	public String viewAll(Model model){
		model.addAttribute("users", userService.findAll());
		return "users-all";
	}
}
