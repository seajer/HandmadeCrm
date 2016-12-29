package ua.lviv.calltech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.service.LanguageService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.ProjectTypeService;

@Controller
public class ProjectController {

	@Autowired
	private LanguageService langService;
	
	@Autowired
	private ProjectTypeService pTypeService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value = "/new_project", method = RequestMethod.GET)
	public String newProject(Model model){
		model.addAttribute("languages", langService.findAll()).
			addAttribute("types", pTypeService.findAll());
		return "project-new";
	}
	
	@RequestMapping(value = "/new_project", method = RequestMethod.POST)
	public String addProject(@RequestParam("name")String name, @RequestParam("company")String company, @RequestParam("lang")int langId,
			@RequestParam("type")int typeId){
		projectService.addProject(name, company, langId, typeId);
		return "redirect:/all_projects";
	}
	
	@RequestMapping(value="/all_projects", method = RequestMethod.GET)
	public String allProjects(Model model){
		model.addAttribute("projects", projectService.findAllWithLanguageAndType());
		return "project-all";
	}
	
	@RequestMapping(value="delete_project_{id}", method = RequestMethod.GET)
	public String deleteProject(@PathVariable int id){
		projectService.deleteProject(id);
		return "redirect:/all_projects";
	}
	
}
