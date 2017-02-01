package ua.lviv.calltech.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.calltech.service.QuestionService;
import ua.lviv.calltech.service.ResultService;

@RestController
public class AjaxController {
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "/saveResultAnswers", method = RequestMethod.POST)
	public @ResponseBody Boolean saveQuestion(@RequestBody String question) {
		JSONObject result = new JSONObject(question);
		JSONObject object = result.getJSONObject("result");
		int resultId = Integer.parseInt(object.getString("resultId").trim());
		int questionId = Integer.parseInt(object.getString("questionId").trim());
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
//		resultService.setAnswerToResult(resultId, questionId, answers);
		return false;
	}
	
	@RequestMapping(value = "/saveTable", method = RequestMethod.POST)
	public @ResponseBody Boolean saveTable(@RequestBody String table){
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
		questionService.saveTable(resultId, tableResults);
		return true;
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
