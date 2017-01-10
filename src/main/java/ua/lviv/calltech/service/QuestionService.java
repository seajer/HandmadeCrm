package ua.lviv.calltech.service;

import ua.lviv.calltech.entity.Question;

public interface QuestionService {

	void hideQuestion(int questionId);

	void addNewQuestion(int questionnaireId, String question, String recommendations, int type, String[] answers);

	Question findById(int questionId);

	void editQuestion(int questionId, String question, String recommendations, int type, int[] answersId,
			String[] answersText);

}
