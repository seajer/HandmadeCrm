package ua.lviv.calltech.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Question;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.QuestionService;
import ua.lviv.calltech.service.ResultService;

@Controller
public class PollController {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value="/new_poll_{id}", method = RequestMethod.GET)
	public String startWorking(@PathVariable("id")int projectId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		int resultId = resultService.findEmptyResultIdByProjectId(projectId, principal.getName());
		Set<Question> quest = questionService.findQuestionsByProjectId(projectId);
		model.addAttribute("projectId", projectId).addAttribute("questions", quest).addAttribute("resultId", resultId);
		return "poll-new";
	}
	
	@RequestMapping(value="/startPoll", method = RequestMethod.POST)
	public String startPoll(@RequestParam("projectId")int projectId, @RequestParam("resultId")int resultId){
		Project project = projectService.findOneWithType(projectId);
		SingleResult result = resultService.findOne(resultId);
		if(project != null && result != null){
			return "redirect:/examined_p="+projectId+"r="+resultId;
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/edit_result_{resultId}", method = RequestMethod.GET)
	public String editPoll(@PathVariable("resultId")int resultId, Model model){
		SingleResult result = resultService.findOneWithAnswersAndProject(resultId);
		Set<Question> quest = questionService.findQuestionsByProjectId(result.getProject().getId());
		Set<Integer> answeredQuestion = questionService.findAnsweredQuestionsForResultById(resultId); 
		model.addAttribute("result", result).addAttribute("questions", quest).addAttribute("answeredQuestions", answeredQuestion);
		return "poll-edit";
	}
	
	@RequestMapping(value="/editPoll", method = RequestMethod.POST)
	public String editPoll(@RequestParam("resultId")int resultId){
		SingleResult result = resultService.findOneWithClient(resultId);
		if(result.getClient() != null){
			return "redirect:/edit_client_"+result.getClient().getId();
		}
		return "redirect:/";
	}

}
