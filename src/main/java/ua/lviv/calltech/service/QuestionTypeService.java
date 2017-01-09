package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.QuestionType;

public interface QuestionTypeService {

	List<QuestionType> findAll();

	void createDefaultTypes();

}
