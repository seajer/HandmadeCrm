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
		int resultId = resultService.findEmptyResultIdByProjectId(projectId, principal.getName());
		Set<Question> quest = questionService.findQuestionsByProjectId(projectId);
		model.addAttribute("projectId", projectId).addAttribute("questions", quest).addAttribute("resultId", resultId);
		return "poll-new";
	}
	
	@RequestMapping(value="/startPoll", method = RequestMethod.POST)
	public String startPoll(@RequestParam("projectId")int projectId, @RequestParam("resultId")int resultId){
		Project project = projectService.findOne(projectId);
		if(project != null){
			
		}
		return "redirect:/";
	}

}
