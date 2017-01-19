package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Result;

public interface ResultService {

	int findEmptyResultIdByProjectId(int projectId, String string);

	Result findOneWithAnswers(int resultId);
	
	Result findOneWithAnswersAndProject(int resultId);

	void setAnswerToResult(int resultId, int questionId, List<String> answers);

	Result findOne(int resultId);

	void save(Result result);

	Result findOneWithClient(int resultId);

	Result findIdByClientId(int id);

}
