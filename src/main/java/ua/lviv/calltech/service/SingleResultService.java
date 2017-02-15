package ua.lviv.calltech.service;

import java.util.List;
import java.util.Map;

import ua.lviv.calltech.entity.SingleResult;

public interface SingleResultService {

	void saveTable(int resultId, Map<Integer, List<String>> tableResults, int principal);

	void saveAnswer(int resultId, int questionId, List<String> answers, int principal);

	List<SingleResult> findAllByResultId(int resultId);

}
