package ua.lviv.calltech.service;

import org.json.JSONArray;

import ua.lviv.calltech.entity.Result;

public interface ResultService {

	int findEmptyResultIdByProjectId(int projectId, String string);

	Result findOneWithAnswers(int resultId);
	
	Result findOneWithAnswersAndProject(int resultId);

	void setAnswerToResult(int resultId, int questionId, JSONArray answers);

	Result findOne(int resultId);

	void save(Result result);

	Result findOneWithClient(int resultId);

	Result findIdByClientId(int id);

}
