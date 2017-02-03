package ua.lviv.calltech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.lviv.calltech.service.ProjectTypeService;
import ua.lviv.calltech.service.QuestionTypeService;
import ua.lviv.calltech.service.RoleService;
import ua.lviv.calltech.service.StatusService;

@Controller
public class MainController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ProjectTypeService pTypeService;
	
	@Autowired
	private QuestionTypeService qTypeService;
	
	@Autowired
	private StatusService statusService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model, Principal princinap) {
		if(roleService.findAll().isEmpty()){
			roleService.saveRoles();
		}
		if(pTypeService.findAll().isEmpty()){
			pTypeService.createProjectTypes();
		}
		if(qTypeService.findAll().isEmpty()){
			qTypeService.createDefaultTypes();
		}
		if(statusService.findAll().isEmpty()){
			statusService.createStatuses();
		}
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
