package ua.lviv.calltech.service;

public interface QuestionService {

	void hideQuestion(int questionId);

	void addNewQuestion(int questionnaireId, String question, String recommendations, int type, String[] answers);

}
