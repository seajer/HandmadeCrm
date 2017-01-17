package ua.lviv.calltech.service;

import org.json.JSONArray;

import ua.lviv.calltech.entity.Result;

public interface ResultService {

	int findEmptyResultIdByProjectId(int projectId, String string);

	Result findOneWithAnswers(int resultId);

	void setAnswerToResult(int resultId, int questionId, JSONArray answers);
}
