package ua.lviv.calltech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.QuestionnaierService;

@Controller
public class QuestionnaireController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private QuestionnaierService questionnireService;
	
	@RequestMapping(value="/new_questionnaire", method = RequestMethod.GET)
	public String newQuestionnaire(Model model){
		model.addAttribute("projects", projectService.findAll());
		return "questionnaire-new";
	}
	
	@RequestMapping(value="/new_questionnaire", method = RequestMethod.POST)
	public String addQuestionnaire(@RequestParam("desctiption")String description, @RequestParam("project")int projectId){
		questionnireService.addQuestionnaire(description, projectId);
		return "redirect:/";
	}
	
	@RequestMapping(value="/view_questionnaire_{id}", method = RequestMethod.GET)
	public String viewQuestionnaire(Model model, @PathVariable int id){
		model.addAttribute("questionnaire", questionnireService.findByIdWithQuestions(id));
		return "questionnaire-view";
	}
}
