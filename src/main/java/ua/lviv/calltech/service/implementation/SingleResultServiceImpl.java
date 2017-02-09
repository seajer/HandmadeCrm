package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Answer;
import ua.lviv.calltech.entity.Question;
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.SingleResultRepository;
import ua.lviv.calltech.service.AnswerService;
import ua.lviv.calltech.service.QuestionService;
import ua.lviv.calltech.service.ResultService;
import ua.lviv.calltech.service.SingleResultService;
import ua.lviv.calltech.service.UserService;

@Service
public class SingleResultServiceImpl implements SingleResultService{
	
	@Autowired
	private SingleResultRepository singleResultRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private QuestionService questionService;

	@Transactional
	public void saveTable(int resultId, Map<Integer, List<String>> tableResults, int principal) {
		for (Integer key : tableResults.keySet()) {
			saveAnswer(resultId, key, tableResults.get(key), principal);
		}
	}

	@Transactional
	public void saveAnswer(int resultId, int questionId, List<String> answers, int principal) {
		SingleResult sr = singleResultRepository.findByResultIdAndQuestionId(resultId, questionId);
		User user = userService.findById(principal);
		for (String string : answers) {
			System.out.println("answer = " + string);
		}
		if(sr != null){
			//edit existing single result
			transformAnswers(answers, sr);
		} else {
			//new single result
			sr = new SingleResult();
			Question q = questionService.findOne(questionId);
			sr.setQuestion(q);
			transformAnswers(answers, sr);
			Result result = resultService.findOne(resultId);
			sr.setTotalResult(result);
		}
		sr.setUser(user);
		singleResultRepository.save(sr);
	}
	
	private void transformAnswers(List<String> answers, SingleResult sr){
		List<Answer> srAnswers = new ArrayList<>();
		String customAnswer = "";
		for (String answer : answers) {
			try{
				//if answer from DB
				Integer answerId = Integer.parseInt(answer);
				Answer nas = answerService.findOne(answerId);
				srAnswers.add(nas);
			} catch (Exception e){
				//if custom answer
				if(customAnswer.length()>1){
					customAnswer += ", " + answer;
				} else {
					customAnswer += answer;
				}
			}
		}
		if(srAnswers.size() > 0){
			sr.setAnswers(srAnswers);
		}
		if(customAnswer.length() > 0){
			sr.setCustomAnswer(customAnswer);
		}
	}

}
