package ua.lviv.calltech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.service.ProjectTypeService;

@Controller
public class ProjectTypeController {
	
	@Autowired
	private ProjectTypeService pTypeServise;
	
	@RequestMapping(value="/new_projecttype", method = RequestMethod.GET)
	public String newProjectType(){
		return "pt-new";
	}
	
	@RequestMapping(value="/new_projecttype", method = RequestMethod.POST)
	public String createProjectType(@RequestParam("type")String type){
		pTypeServise.addNewPType(type);
		return "redirect:/all_projectTypes";
	}
	
	@RequestMapping(value="/all_projectTypes", method = RequestMethod.GET)
	public String allProjectTypes(Model model){
		model.addAttribute("types", pTypeServise.findAll());
		return "pt-all";
	}
	@RequestMapping(value="/delete_projectType_{id}") 
	public String deleteProjectType(@PathVariable("id")int projectTypeId){
		pTypeServise.deletePType(projectTypeId);
		return "redirect:/all_projectTypes";
	}
	
}
