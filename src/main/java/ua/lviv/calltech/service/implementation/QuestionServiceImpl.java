package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
		Question q = questionRepository.findOne(questionId);
		QuestionType qt = questionTypeRepository.findByQuestionId(questionId);
		if(qt.getId() == 14 || qt.getId() == 15 || qt.getId() == 16){
			List<Question> table = questionRepository.findAllQuestionTable(questionId);
			List<Answer> answers = answerRepository.findAllByQuestionId(questionId);
			q.setTableQuestions(table);
			q.setAnswers(answers);
		} else if (qt.getId() == 13){
			
		} else {
			List<Answer> answers = answerRepository.findAllByQuestionId(questionId);
			q.setAnswers(answers);
		}
		q.setType(qt);
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
		for (Question question : questions) {
			List<Question> table = questionRepository.findAllQuestionTable(question.getId());
			question.setTableQuestions(table);
		}
		return questions;
	}

	public Set<Integer> findAnsweredQuestionsForResultById(int resultId) {
		Set<Integer> answeredQuestions = questionRepository.findAnsweredByResultId(resultId);
		return answeredQuestions;
	}

	@Transactional
	public void addTable(int questionnaireId, int type, String recomendations, String[] question, String[] answer) {
		Question qq = new Question();
		List<Answer> answers = new ArrayList<Answer>();
		for (String answer2 : answer) {
			Answer a = new Answer(answer2);
			a.setQuestion(qq);
			answers.add(a);
		}
		answerRepository.save(answers);
		List<Question> questions = new ArrayList<Question>();
		for (String question2 : question) {
			Question q = new Question(question2);
			q.setAnswers(answers);
			q.setTable(qq);
			questions.add(q);
		}
		questionRepository.save(questions);
		Questionnaire questionnaire = questionnaireRepository.findOne(questionnaireId);
		QuestionType qt = questionTypeRepository.findOne(type);
		qq.setRecomendations(recomendations);
		qq.setType(qt);
		qq.setTableQuestions(questions);
		qq.setQuestionnaire(questionnaire);
		questionRepository.save(qq);
	}

	public void saveTable(int resultId, Map<Integer, List<String>> tableResults) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void addOpen(int questionnaireId, String question, int type, String recomendations) {
		QuestionType qt = questionTypeRepository.findOne(type);
		Questionnaire questionnaire = questionnaireRepository.findOne(questionnaireId);
		Question quest = new Question(question);
		quest.setType(qt);
		quest.setQuestionnaire(questionnaire);
		quest.setRecomendations(recomendations);
		questionRepository.save(quest);
	}

	@Transactional
	public void editOpen(int questionId, String question, String recommendations, int type) {
		Question quest = questionRepository.findOne(questionId);
		quest.setAnswers(null);
		quest.setText(question);
		quest.setRecomendations(recommendations);
		quest.setType(questionTypeRepository.findOne(type));
		questionRepository.save(quest);
	}

	@Transactional
	public void editTable(int questionId, String recommendations, int type, int[] questionIds, String[] questions,
			int[] answerIds, String[] answers) {
		Question q = questionRepository.findOne(questionId);
		if(q != null){
			q.setRecomendations(recommendations);
			QuestionType qt = questionTypeRepository.findOne(type);
			if(qt != null){
				q.setType(qt);
			}

			setQuestions(q, questionIds, questions);
			setAnswers(q, answerIds, answers);
			
			questionRepository.save(q);
		}
	}
	
	private void setQuestions(Question q, int[] questionIds, String[] questions){
		for(int i = 0; i < questionIds.length; i++){
			Question quest = questionRepository.findOne(questionIds[i]);
			if(quest != null){
				quest.setText(questions[i]);
				questionRepository.save(quest);
			}
		}
		if(questions.length > questionIds.length){
			List<Question> quests = new ArrayList<Question>();
			for(int i = questionIds.length; i < questions.length; i++){
				Question quest = new Question(questions[i]);
				quest.setTable(q);
				questionRepository.save(quest);
				quests.add(quest);
			}
			quests.addAll(q.getTableQuestions());
			q.setTableQuestions(quests);
		}
	}
	
	private void setAnswers(Question q, int[] answerIds, String[] answers){
		for(int i = 0; i < answerIds.length; i++){
			Answer answer = answerRepository.findOne(answerIds[i]);
			if(answer != null){
				answer.setText(answers[i]);
				answerRepository.save(answer);
			}
		}
		if(answers.length > answerIds.length){
			List<Answer> answ = new ArrayList<Answer>();
			for(int i = answerIds.length; i < answers.length; i++){
				Answer ans = new Answer(answers[i]);
				ans.setQuestion(q);
				answerRepository.save(ans);
			}
			answ.addAll(q.getAnswers());
			q.setAnswers(answ);
		}
	}
	
}
