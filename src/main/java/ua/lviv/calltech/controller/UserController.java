package ua.lviv.calltech.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.service.LanguageService;
import ua.lviv.calltech.service.RoleService;
import ua.lviv.calltech.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value = "/new_user", method = RequestMethod.GET)
	public String newUser(Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		if(roleService.findAll().size()<1){
			roleService.saveRoles();
		}
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("languages", languageService.findAll());
		return "users-new";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestParam("name")String name, @RequestParam("email")String email,
			@RequestParam("phone")String phone, @RequestParam("password")String password, @RequestParam("role")int roleId,
			@RequestParam("language")int langId){
		userService.addUser(name, email, phone, password, roleId, langId);
		return "redirect:/all_users";
	}
	
	@RequestMapping(value="/delete_user_{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		userService.deleteUser(id);
		return "redirect:/all_users";
	}
	
	@RequestMapping(value="/all_users", method = RequestMethod.GET)
	public String viewAll(Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		model.addAttribute("users", userService.findAll());
		return "users-all";
	}
	
	@RequestMapping(value="/edit_user_{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable("userId")int userId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		User user = userService.findById(userId);
		List<Language> langsIn = languageService.findAllByUserId(userId);
		List<Language> langsOut = languageService.findAllExcept(langsIn);
		model.addAttribute("user", user).addAttribute("langsIn", langsIn).addAttribute("langsOut", langsOut);
		return "users-edit";
	}
	
	@RequestMapping(value="/edit_user", method=RequestMethod.POST)
	public String edit(@RequestParam("id")int userId,@RequestParam("name")String name, @RequestParam("email")String email,
			@RequestParam("phone")String phone,	@RequestParam("oldPassword")String oldPass,
			@RequestParam("newPassword")String newPass, @RequestParam("lang")int[] langsId){
		boolean passPresented = userService.passPresenting(oldPass);
		if(passPresented){
			boolean validPAss = userService.validatePassword(oldPass, userId);
			if(validPAss){
				userService.saveWithPass(userId, name, email, phone, newPass, langsId);
			}else{
				return "redirect:/edit_user_"+userId+"?wrongPass=true";
			}
		}else{
			userService.saveWithoutPass(userId, name, email, phone, langsId);
		}
		return "redirect:/all_users";
	}
}
