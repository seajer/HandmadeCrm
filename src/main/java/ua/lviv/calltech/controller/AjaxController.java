package ua.lviv.calltech.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.calltech.service.ResultService;

@RestController
public class AjaxController {
	
	@Autowired
	private ResultService resultService;
	
	@RequestMapping(value = "/saveResultAnswers", method = RequestMethod.POST)
	public @ResponseBody Boolean saveCommentForVideo(@RequestBody String video) {
		JSONObject object = new JSONObject(video);
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
		resultService.setAnswerToResult(resultId, questionId, answers);
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
