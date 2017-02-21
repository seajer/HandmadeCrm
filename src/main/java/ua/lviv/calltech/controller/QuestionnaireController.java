package ua.lviv.calltech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.DTO.QuestionnaireDTO;
import ua.lviv.calltech.entity.Questionnaire;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.QuestionnaierService;

@Controller
public class QuestionnaireController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private QuestionnaierService questionnaireService;
	
	@RequestMapping(value="/new_questionnaire", method = RequestMethod.GET)
	public String newQuestionnaire(Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		model.addAttribute("projects", projectService.findAll());
		return "questionnaire-new";
	}
	
	@RequestMapping(value="/new_questionnaire", method = RequestMethod.POST)
	public String addQuestionnaire(@RequestParam("desctiption")String description, @RequestParam("project")int projectId){
		questionnaireService.addQuestionnaire(description, projectId);
		int questionnaireId = questionnaireService.findByDescriptionAndProject(description, projectId);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/all_questionnaire", method = RequestMethod.GET)
	public String allQuestionnaire(Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		model.addAttribute("questionaire", questionnaireService.findAll());
		return "questionnaire-all";
	}
	
	@RequestMapping(value="/view_questionnaire_{id}", method = RequestMethod.GET)
	public String viewQuestionnaire(Model model, @PathVariable int id, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		QuestionnaireDTO quest = questionnaireService.findDtoByIdWithQuestionsAndVisible(id, true);
		if (quest != null){
			model.addAttribute("questionnaire", quest);
			return "questionnaire-view";
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/edit_questionnaire_{id}", method = RequestMethod.GET)
	public String editQuestionnaire(@PathVariable("id")int questionnaireId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		Questionnaire quest = questionnaireService.findByIdWithQuestions(questionnaireId, true);
		model.addAttribute("questionnaire", quest);
		return "questionnaire-edit";
	}
	
	@RequestMapping(value="/edit_questionnaire", method = RequestMethod.POST)
	public String edit(@RequestParam("id")int questionnaireId, @RequestParam("description")String description){
		if(description.trim().length() > 1){
			questionnaireService.editQUestionnaire(questionnaireId, description);
		}
		return "redirect:/all_questionnaire";
	}
}
