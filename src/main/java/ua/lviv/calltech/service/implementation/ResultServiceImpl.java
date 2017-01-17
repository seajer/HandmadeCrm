package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Answer;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.ResultRepository;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.AnswerService;
import ua.lviv.calltech.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AnswerService answerService;
	
	@Transactional
	public int findEmptyResultIdByProjectId(int projectId, String principalId) {
		int userId = Integer.parseInt(principalId);
		Integer resultId = resultRepository.finqClearOneByProjectId(projectId, userId);
		if(resultId == null){
			Project project = projectRepository.findOne(projectId);
			User user = userRepository.findOne(userId);
			Result r = new Result(project, user);
			resultRepository.save(r);
			resultId = resultRepository.finqClearOneByProjectId(projectId, userId);
		}
		return resultId;
	}

	// не паше тому ..
	public Result findOneWithAnswers(int resultId) {
		Result result = resultRepository.findOneWithAnswers(resultId);
		return result;
	}
//	// .. їбашим отак
//	private Result costylMethod(int resultId){
//		Result result = resultRepository.findOne(resultId);
//		List<Answer> answers = answerService.findAllByResultId(resultId);
//		result.setAnswers(answers);
//		return result;
//	}

	@Transactional
	public void setAnswerToResult(int resultId, int questionId, JSONArray answers) {
		Result result = findOneWithAnswers(resultId);
		for (Object obj : answers) {
			try {
				int answerId = Integer.parseInt(obj.toString());
				Answer answer = answerService.findOne(answerId);
				setAnswersToResult(result, answer);
				
			} catch (Exception e) {
				String str = obj.toString();
				Answer answer = new Answer(str);
				answer.setQuestionId(questionId);
				answerService.save(answer);
				setAnswersToResult(result, answer);
			}
			
		}
		resultRepository.save(result);
	}
	
	private void setAnswersToResult(Result result, Answer answer){
		if(result.getAnswers() == null){
			List<Answer> answ = new ArrayList<Answer>();
			answ.add(answer);
			result.setAnswers(answ);
		} else{
			List<Answer> answ = result.getAnswers();
			answ.add(answer);
			result.setAnswers(answ);
		}
	}

}
