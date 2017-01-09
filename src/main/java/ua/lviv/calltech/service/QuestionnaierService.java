package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.DTO.QuestionnaireDTO;
import ua.lviv.calltech.entity.Questionnaire;

public interface QuestionnaierService {

	void addQuestionnaire(String description, int projectId);

	Questionnaire findByIdWithQuestions(int id, boolean isVisible);

	Questionnaire findById(int questionnaireId);

	List<Questionnaire> findAll();

	QuestionnaireDTO findDtoByIdWithQuestionsAndVisible(int id, boolean b);

}
