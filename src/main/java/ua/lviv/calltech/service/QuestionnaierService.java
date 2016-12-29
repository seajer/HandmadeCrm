package ua.lviv.calltech.service;

import ua.lviv.calltech.entity.Questionnaire;

public interface QuestionnaierService {

	void addQuestionnaire(String description, int projectId);

	Questionnaire findByIdWithQuestions(int id);

}
