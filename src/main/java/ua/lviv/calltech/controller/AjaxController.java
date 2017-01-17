package ua.lviv.calltech.controller;

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
		JSONArray answers = object.getJSONArray("answers");
		resultService.setAnswerToResult(resultId, questionId, answers);
		return false;
	}
}
