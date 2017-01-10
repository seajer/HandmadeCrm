package ua.lviv.calltech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.entity.Answer;
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
	
	@RequestMapping(value="/edit_question_{questionId}", method = RequestMethod.GET)
	public String editQuestion(@PathVariable("questionId")int questionId, Model modal){
		Question question = questionService.findById(questionId);
		List<QuestionType> types = questionTypeServise.findAll();
		types.remove(question.getType());
		System.out.println("question id = " + questionId);
		System.out.println("question text " + question.getText());
		System.out.println("recomendations " + question.getRecomendations());
		System.out.println("question type id = " + question.getType().getId());
		for(Answer answer : question.getAnswers()){
			System.out.println("answer id = " + answer.getId() + " answer text = " + answer.getText());
		}
		modal.addAttribute("question", question).addAttribute("types", types);
		return "question-edit";
	}
	
	@RequestMapping(value="/edit_question", method = RequestMethod.POST)
	public String edit(@RequestParam("id")int questionId, @RequestParam("question")String question, @RequestParam("recommendations")String recommendations,
			@RequestParam("answType")int type, @RequestParam("answerId")int[] answersId, @RequestParam("answerText")String[] answersText){
		System.out.println("question id = " + questionId);
		System.out.println("question text " + question);
		System.out.println("recomendations " + recommendations);
		System.out.println("question type id = " + type);
		for(int i = 0; i < answersId.length; i++){
			System.out.println("answer id = " + answersId[i] + " answer text = " + answersText[i]);
		}
		questionService.editQuestion(questionId, question, recommendations, type, answersId, answersText);
		return "redirect:/";
	}
	
	@RequestMapping(value="hide_question_{id}", method=RequestMethod.GET)
	public String hideQuestion(@PathVariable("id")int questionId){
		questionService.hideQuestion(questionId);
		return "redirect:/";
	}
}
