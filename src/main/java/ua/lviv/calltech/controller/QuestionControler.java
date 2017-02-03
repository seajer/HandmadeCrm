package ua.lviv.calltech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.entity.Question;
import ua.lviv.calltech.entity.QuestionType;
import ua.lviv.calltech.entity.Questionnaire;
import ua.lviv.calltech.service.QuestionService;
import ua.lviv.calltech.service.QuestionTypeService;
import ua.lviv.calltech.service.QuestionnaierService;

@Controller
public class QuestionControler {
	
	@Autowired
	private QuestionnaierService questionnaireService;
	@Autowired
	private QuestionTypeService questionTypeServise;
	@Autowired
	private QuestionService questionService;
	
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
			@RequestParam("recommendations")String recommendations, @RequestParam("answType")int type,
			@RequestParam("answer")String[] answers){
		questionService.addNewQuestion(questionnaireId, question, recommendations, type, answers);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/new_table", method = RequestMethod.POST)
	public String newTable(@RequestParam("questionnaireId")int questionnaireId, @RequestParam("answType")int type,
			@RequestParam("question")String[] question, @RequestParam("answer")String[] answer,
			@RequestParam("recommendations")String recomendations){
		questionService.addTable(questionnaireId, type, recomendations, question, answer);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/new_open", method = RequestMethod.POST)
	public String newOpen(@RequestParam("questionnaireId")int questionnaireId, @RequestParam("answType")int type,
			@RequestParam("question")String question, @RequestParam("recommendations")String recomendations){
		questionService.addOpen(questionnaireId, question, type, recomendations);
		return "redirect:/view_questionnaire_"+questionnaireId; 
	}
	
	@RequestMapping(value="/edit_question_{questionId}", method = RequestMethod.GET)
	public String editQuestion(@PathVariable("questionId")int questionId, Model modal){
		Question question = questionService.findById(questionId);
		List<QuestionType> types = questionTypeServise.findAll();
		types.remove(question.getType());
		modal.addAttribute("question", question).addAttribute("types", types);
		return "question-edit";
	}
	
	@RequestMapping(value="/edit_question", method = RequestMethod.POST)
	public String editQuestion(@RequestParam("id")int questionId, @RequestParam("question")String question, 
			@RequestParam("recommendations")String recommendations,	@RequestParam("answType")int type,
			@RequestParam("answerId")int[] answersId, @RequestParam("answerText")String[] answersText){
		questionService.editQuestion(questionId, question, recommendations, type, answersId, answersText);
		int questionnaireId = questionnaireService.findIdByQuestionId(questionId);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/edit_open", method = RequestMethod.POST)
	public String editOpeb(@RequestParam("id")int questionId, @RequestParam("question")String question,
			@RequestParam("recommendations")String recommendations, @RequestParam("answType")int type){
		questionService.editOpen(questionId, question, recommendations, type);
		int questionnaireId = questionnaireService.findIdByQuestionId(questionId);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/edit_table", method = RequestMethod.POST)
	public String editTable(@RequestParam("id")int questionId, @RequestParam("recommendations")String recommendations,
			@RequestParam("answType")int type, @RequestParam("answerId")int[] answerIds, @RequestParam("answer")String[] answers,
			@RequestParam("question")String[] questions, @RequestParam("questionsId")int[] questionIds){
		questionService.editTable(questionId, recommendations, type, questionIds, questions, answerIds, answers);
		int questionnaireId = questionnaireService.findIdByQuestionId(questionId);
		return "redirect:/view_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/hide_question_{id}", method=RequestMethod.GET)
	public String hideQuestion(@PathVariable("id")int questionId){
		questionService.setVisible(questionId, false);
		int questionnaireId = questionnaireService.findIdByQuestionId(questionId);
		return "redirect:/edit_questionnaire_"+questionnaireId;
	}
	
	@RequestMapping(value="/show_question_{id}", method=RequestMethod.GET)
	public String showQuestion(@PathVariable("id")int questionId){
		questionService.setVisible(questionId, true);
		int questionnaireId = questionnaireService.findIdByQuestionId(questionId);
		return "redirect:/edit_questionnaire_"+questionnaireId;
	}
}
