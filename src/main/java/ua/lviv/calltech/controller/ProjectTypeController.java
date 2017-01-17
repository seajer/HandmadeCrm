package ua.lviv.calltech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.entity.ProjectType;
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
		List<ProjectType> types = pTypeServise.findAll();
		if(types.size() < 1){
			pTypeServise.createProjectTypes();
			types = pTypeServise.findAll();
		}
		model.addAttribute("types", types);
		return "pt-all";
	}
	@RequestMapping(value="/delete_projectType_{id}", method = RequestMethod.GET) 
	public String deleteProjectType(@PathVariable("id")int projectTypeId){
		pTypeServise.deletePType(projectTypeId);
		return "redirect:/all_projectTypes";
	}
	
	@RequestMapping(value="/edit_projectType", method = RequestMethod.POST)
	public String edit(@RequestParam("id")int projectTypeId, @RequestParam("name")String name){
		if(name.trim().length() > 1){
			pTypeServise.edit(projectTypeId, name);
		}
		return "redirect:/all_projectTypes";
	}
	
}
