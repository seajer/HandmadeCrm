package ua.lviv.calltech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.entity.QuestionType;
import ua.lviv.calltech.entity.Questionnaire;
import ua.lviv.calltech.service.QuestionTypeService;
import ua.lviv.calltech.service.QuestionnaierService;

@Controller
public class QuestionControler {
	
	@Autowired
	private QuestionnaierService questionnaireService;
	@Autowired
	private QuestionTypeService questionTypeServise;
	
	@RequestMapping(value="/new_question_{questionnaireId}", method = RequestMethod.GET)
	public String viewQuestion(Model model, @PathVariable int questionnaireId){
		Questionnaire quest = questionnaireService.findById(questionnaireId);
		if(quest != null){
			List<QuestionType> types = questionTypeServise.findAll();
			if(types.size()<1){
				questionTypeServise.createDefaultTypes();
				types = questionTypeServise.findAll();
			}
			model.addAttribute("questionaireId", questionnaireId).addAttribute("questionTypes", types);
			return "question-new";
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/new_question", method = RequestMethod.POST)
	public String newQuestion(@RequestParam("questionnaireId")int questionnaireId, @RequestParam("question")String question,
			@RequestParam("recommendations")String recommendations, @RequestParam("answType")int type){
		System.out.println("questionnaire id = " + questionnaireId);
		System.out.println("question = " + question);
		System.out.println("recommendations = " + recommendations);
		System.out.println("answer type = " + type);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
}
