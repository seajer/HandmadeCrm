package ua.lviv.calltech.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model, Principal princinap) {
		if(princinap != null){
			return "admin-index";
		} else {
			return "redirect:/loginpage";
		}
		
	}
	
	@RequestMapping(value="/loginpageFail", method = RequestMethod.GET)
	public String loginFail (RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("message", "Wrong login or password");
		return "redirect:/loginpage";
	}

	@RequestMapping(value="/loginpage", method=RequestMethod.GET)
	public String login() {
		return "login";
}
}
