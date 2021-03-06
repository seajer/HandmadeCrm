package ua.lviv.calltech.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.calltech.service.ResultService;
import ua.lviv.calltech.service.SingleResultService;

@RestController
public class AjaxController {
	
	@Autowired
	private SingleResultService singleAnswerServce;
	
	@Autowired
	private ResultService resultService;
	
	@RequestMapping(value = "/saveResultAnswers", method = RequestMethod.POST)
	public @ResponseBody Boolean saveQuestion(@RequestBody String question, Principal principal) {
		JSONObject result = new JSONObject(question);
		JSONObject object = result.getJSONObject("result");
		int resultId = object.getInt("resultId");
		int questionId = object.getInt("questionId");
		String answerString = null;
		JSONArray answerArray = null;
		List<String> answers = new ArrayList<String>();
		try {
			answerArray = object.getJSONArray("answers");
			
		} catch (Exception e) {
			answerString = object.getString("answers");
		}
		
		if(answerString != null){
			answers = changeStringToStringList(answerString);
		}else{
			answers = changeJsonArrayToStringList(answerArray);
		}
		singleAnswerServce.saveAnswer(resultId, questionId, answers, Integer.parseInt(principal.getName()));
		return false;
	}
	
	@RequestMapping(value = "/saveTable", method = RequestMethod.POST)
	public @ResponseBody Boolean saveTable(@RequestBody String table, Principal principal){
		JSONObject object = new JSONObject(table);
		JSONObject res = object.getJSONObject("result");
		int resultId = res.getInt("resultId");
		JSONArray results = res.getJSONArray("results");
		Map<Integer, List<String>> tableResults = new LinkedHashMap<Integer, List<String>>();
		for(int i = 0; i < results.length(); i++){
			JSONObject question = results.getJSONObject(i);
			int questionId = question.getInt("questionId");
			JSONArray answers = question.getJSONArray("answers");
			List<String> ans = new ArrayList<String>();
			for(int j = 0; j < answers.length(); j++){
				String answer = answers.getString(j);
				ans.add(answer);
			}
			tableResults.put(questionId, ans);
		}
		singleAnswerServce.saveTable(resultId, tableResults, Integer.parseInt(principal.getName()));
		return true;
	}
	
	@RequestMapping(value = "/changeStatus_s={statusId}_r={resuntId}_rec={recallTime}", method = RequestMethod.GET)
	public @ResponseBody Boolean changeStatus(@PathVariable("statusId")int statusId, @PathVariable("resuntId")int resultId,
			@PathVariable("recallTime")String recall){
		if(recall.equals("0"))recall=null;
		resultService.changeStatus(statusId, resultId, recall);
		return false;
	}
	
	private List<String> changeStringToStringList(String answer){
		List<String> answers = new ArrayList<String>();
		String[] separated = answer.split(",");
		for (String string : separated) {
			answers.add(string.trim());
		}
		return answers;
	}
	
	private List<String> changeJsonArrayToStringList(JSONArray answer){
		List<String> answers = new ArrayList<String>();
		for (Object object : answer) {
			String ans = object.toString().trim();
			answers.add(ans);
		}
		return answers;
	}
}
