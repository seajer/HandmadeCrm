package ua.lviv.calltech.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
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
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.entity.Status;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.QuestionService;
import ua.lviv.calltech.service.ResultService;
import ua.lviv.calltech.service.SingleResultService;
import ua.lviv.calltech.service.StatusService;

@Controller
public class PollController {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ClientDataObjectService cdoService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private SingleResultService singleResultService;

	@RequestMapping(value="/new_poll_{id}", method = RequestMethod.GET)
	public String startWorking(@PathVariable("id")int projectId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		Project p = projectService.findOne(projectId);
		if(p == null){
			return "redirect:/all_projects";
		}
		model.addAttribute("projectId", projectId);
		return "client-check";
	}
	
	@RequestMapping(value="/check_client", method = RequestMethod.POST)
	public String checkClient(@RequestParam("projectId")int projectId, @RequestParam("phone")String phone, @RequestParam("company")String company, Model model){
		List<Result> results = resultService.findByProjectPhoneAndCompany(projectId, phone, company);
		if(results.size()>0){
			model.addAttribute("results", results);
			return "project-clients";
		} else{
			cdoService.createOne(phone, company);
			resultService.createOne(phone, company, projectId);
			List<Result> result = resultService.findByProjectPhoneAndCompany(projectId, phone, company);
			model.addAttribute("results", result);
			return "project-clients";
		}
	}
	
	@RequestMapping(value="/edit_result_{resultId}", method = RequestMethod.GET)
	public String editPoll(@PathVariable("resultId")int resultId, Model model, Principal principal){
		Integer projectId = projectService.findIdByResultId(resultId);
		Set<Question> questions = questionService.findQuestionsByProjectId(projectId);
		List<SingleResult> results = singleResultService.findAllByResultId(resultId);
		Map<Integer, List<Integer>> tableAnswers = questionService.findTableAnswers(results);
		Map<Integer, String> customAnsw = questionService.findCustomAnswers(results);
		model.addAttribute("questions", questions).addAttribute("principal", principal.getName())
		.addAttribute("tableAnswers", tableAnswers).addAttribute("customAnswers", customAnsw);
		return "poll-edit";
	}
	
	@RequestMapping(value="/editPoll", method = RequestMethod.POST)	
	public String editPoll(@RequestParam("resultId")int resultId, Model model){
		int clientId = cdoService.findIdByResultId(resultId);
		Status st = statusService.findByResultId(resultId);
		List<Status> statuses = statusService.findAll();
		statuses.remove(st);
		model.addAttribute("resultId", resultId).addAttribute("clientId", clientId)
		.addAttribute("status", st).addAttribute("statuses", statuses);
		return "poll-status";
	}

}
