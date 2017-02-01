package ua.lviv.calltech.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.lviv.calltech.entity.Question;

public interface QuestionService {

	void setVisible(int questionId, boolean isVisible);

	void addNewQuestion(int questionnaireId, String question, String recommendations, int type, String[] answers);

	Question findById(int questionId);

	void editQuestion(int questionId, String question, String recommendations, int type, int[] answersId,
			String[] answersText);

	Set<Question> findQuestionsByProjectId(int projectId);

	Set<Integer> findAnsweredQuestionsForResultById(int resultId);

	void addTable(int questionnaireId, int type, String recomendations, String[] question, String[] answer);

	void saveTable(int resultId, Map<Integer, List<String>> tableResults);

}
