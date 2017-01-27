package ua.lviv.calltech.service;

import java.util.List;
import java.util.Map;

import ua.lviv.calltech.entity.SingleResult;

public interface ResultService {

	int findEmptyResultIdByProjectId(int projectId, String string);

	SingleResult findOneWithAnswers(int resultId);
	
	SingleResult findOneWithAnswersAndProject(int resultId);

	void setAnswerToResult(int resultId, int questionId, List<String> answers);

	SingleResult findOne(int resultId);

	void save(SingleResult result);

	SingleResult findOneWithClient(int resultId);

	SingleResult findIdByClientId(int id);

	void saveTable(int resultId, Map<Integer, List<String>> tableResults);

}
