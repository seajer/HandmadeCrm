package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Answer;

public interface AnswerService {

	Answer findOne(int answerId);
	
	void save(Answer ansver);

	List<Answer> findAllByResultId(int resultId);

}
