package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	public void setVisible(int questionId, boolean isVisible) {
		Question q = questionRepository.findOne(questionId);
		q.setVisible(isVisible);
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
		
		q.setAnswers(answerList);
		questionRepository.save(q);
	}

	@Transactional
	public Question findById(int questionId) {
		Question q = questionRepository.findOneWIthType(questionId);
		return q;
	}

	@Transactional
	public void editQuestion(int questionId, String text, String recommendations, int type, int[] answersId,
			String[] answersText) {
		Question question = questionRepository.findOne(questionId);
		QuestionType qt = questionTypeRepository.findOne(type);
		question.setText(text);
		question.setRecomendations(recommendations);
		question.setType(qt);
		List<Answer> incomingAnswers = generatingAnswers(answersText, answersId, questionId);
		List<Answer> answersFromDb = question.getAnswers();
		Iterator<Answer> iter = answersFromDb.iterator();
		while(iter.hasNext()){
			Answer answer = (Answer) iter.next();
			if(incomingAnswers.contains(answer)){
				iter.remove();
			}
		}
		answerRepository.delete(answersFromDb);
		question.setAnswers(incomingAnswers);
		questionRepository.save(question);
	}

	private List<Answer> generatingAnswers(String[] answersText, int[] answersId, int questionId){
		List<Answer> answers = new ArrayList<Answer>();
		for(int i=0; i<answersId.length; i++){
			if(answersId[i]!=0){
				Answer answer = answerRepository.findOne(answersId[i]);
				answer.setText(answersText[i]);
				answerRepository.save(answer);
				answers.add(answer);
			}else{
				Answer answer = new Answer(answersText[i]);
				answer.setQuestion(questionRepository.findOne(questionId));
				answerRepository.save(answer);
				answers.add(answer);
			}
		}
		return answers;
	}

	public Set<Question> findQuestionsByProjectId(int projectId) {
		Set<Question> questions = questionRepository.findAllByProjectId(projectId);
		return questions;
	}
	
}
