package ua.lviv.calltech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.DTO.UserDTO;
import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.ProjectType;
import ua.lviv.calltech.service.LanguageService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.ProjectTypeService;
import ua.lviv.calltech.service.UserService;

@Controller
public class ProjectController {

	@Autowired
	private LanguageService langService;
	
	@Autowired
	private ProjectTypeService pTypeService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private LanguageService languageServise;
	
	@Autowired
	private UserService userServise;
	
	@RequestMapping(value = "/new_project", method = RequestMethod.GET)
	public String newProject(Model model){
		model.addAttribute("languages", langService.findAll());
		List <ProjectType> types = pTypeService.findAll();
		if(types.size() < 1){
			pTypeService.createProjectTypes();
			types = pTypeService.findAll();
		}
		model.addAttribute("types", types);
		return "project-new";
	}
	
	@RequestMapping(value = "/new_project", method = RequestMethod.POST)
	public String addProject(@RequestParam("name")String name, @RequestParam("company")String company, @RequestParam("lang")int langId,
			@RequestParam("type")int typeId){
		projectService.addProject(name, company, langId, typeId);
		ProjectType pt = pTypeService.findById(typeId);
		Project project = projectService.findByNaneAndCompany(name, company);
		if(pt.getName().equals("Poll with customers DB")){
			return "redirect:/uploadCustomerDB_"+project.getId();
		}
		return "redirect:/all_projects";
	}
	
	@RequestMapping(value="/all_projects", method = RequestMethod.GET)
	public String allProjects(Model model){
		model.addAttribute("projects", projectService.findAllWithLanguageAndType());
		return "project-all";
	}
	
	@RequestMapping(value="/edit_project_{id}", method = RequestMethod.GET)
	public String editProject(@PathVariable("id") int projectId, Model model){
		ProjectDTO project = projectService.findDtoById(projectId);
		if(project != null){
			List<Language> otherLangs = languageServise.languagesExceptProjects(project);
			List<ProjectType> otherTypes = pTypeService.typesExceptProjects(project);
			List<UserDTO> usersIn = userServise.findAllUsersDtoOnProject(projectId);
			List<UserDTO> usersOut = userServise.findAllUsersDtoOutOfProject(usersIn, project.getLanguage().getId());
			model.addAttribute("project", project).addAttribute("otherLangs", otherLangs).addAttribute("types", otherTypes);
			model.addAttribute("usersIn", usersIn).addAttribute("usersOut", usersOut);
			return "project-edit";
		}
		return "404";
	}
	
	@RequestMapping(value="/edit_project", method = RequestMethod.POST)
	public String edit(@RequestParam("id")int projectId, @RequestParam("name")String title, @RequestParam("company")String company,
			@RequestParam("lang")int langId, @RequestParam("type")int typeId, @RequestParam("users")int[] usersId){
		projectService.editProject(projectId, title, company, langId, typeId, usersId);
		return "redirect:/all_projects";
	}
	
	@RequestMapping(value="delete_project_{id}", method = RequestMethod.GET)
	public String deleteProject(@PathVariable int id){
		projectService.deleteProject(id);
		return "redirect:/all_projects";
	}
	
}
