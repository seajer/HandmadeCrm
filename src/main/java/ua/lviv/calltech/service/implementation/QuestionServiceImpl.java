package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Answer;
import ua.lviv.calltech.entity.Question;
import ua.lviv.calltech.entity.QuestionType;
import ua.lviv.calltech.entity.Questionnaire;
import ua.lviv.calltech.repository.AnswerRepository;
import ua.lviv.calltech.repository.QuestionRepository;
import ua.lviv.calltech.repository.QuestionTypeRepository;
import ua.lviv.calltech.repository.QuestionnaireRepository;
import ua.lviv.calltech.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Transactional
	public void hideQuestion(int questionId) {
		Question q = questionRepository.findOne(questionId);
		q.setVisible(false);
		questionRepository.save(q);
	}

	@Transactional
	public void addNewQuestion(int questionnaireId, String question, String recommendations, int type, String[] answers) {
		Questionnaire questionnaire = questionnaireRepository.findOne(questionnaireId);
		QuestionType qt = questionTypeRepository.findOne(type);
		Question q = new Question(question, recommendations, qt, questionnaire, true);
		
		List<Answer> answerList = new ArrayList<Answer>();
		for (String string : answers) {
			Answer answer = new Answer(string);
			answer.setQuestion(q);
			answerList.add(answer);
		}
		answerRepository.save(answerList);
		
		q.setAnswears(answerList);
		questionRepository.save(q);
	}

}
