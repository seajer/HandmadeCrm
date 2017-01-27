package ua.lviv.calltech.service.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.ResultRepository;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private AnswerService answerService;
	
	@Transactional
	public int findEmptyResultIdByProjectId(int projectId, String principalId) {
		int userId = Integer.parseInt(principalId);
		Integer resultId = resultRepository.finqClearOneByProjectId(projectId, userId);
		if(resultId == null){
			Project project = projectRepository.findOne(projectId);
			User user = userRepository.findOne(userId);
			SingleResult r = new SingleResult(project, user);
			resultRepository.save(r);
			resultId = resultRepository.finqClearOneByProjectId(projectId, userId);
		}
		return resultId;
	}

	@Transactional
	public SingleResult findOneWithAnswers(int resultId) {
		SingleResult result = resultRepository.findOneWithAnswers(resultId);
		return result;
	}

	@Transactional
	public void setAnswerToResult(int resultId, int questionId, List<String> answers) {
		// TODO: rewrite this method
	}
	
//	private void setAnswersToResult(SingleResult result, Answer answer){
//		if(result.getAnswers() == null){
//			List<Answer> answ = new ArrayList<Answer>();
//			answ.add(answer);
//			result.setAnswers(answ);
//		} else{
//			List<Answer> answ = result.getAnswers();
//			if(!answ.contains(answer)){
//				answ.add(answer);
//			}
//			result.setAnswers(answ);
//		}
//	}

	public SingleResult findOne(int resultId) {
		return resultRepository.findOne(resultId);
	}

	@Transactional
	public void save(SingleResult result) {
		resultRepository.save(result);
	}

	public SingleResult findOneWithClient(int resultId) {
		SingleResult result = resultRepository.findOneWithClient(resultId);
		return result;
	}

	@Transactional
	public SingleResult findOneWithAnswersAndProject(int resultId) {
		SingleResult result = resultRepository.findOneWithAnswersAndProject(resultId);
		return result;
	}

	@Transactional
	public SingleResult findIdByClientId(int id) {
		SingleResult r = resultRepository.findIdByClientId(id);
		return r;
	}

	@Transactional
	public void saveTable(int resultId, Map<Integer, List<String>> tableResults) {
		// TODO: rewrite this method
	}
}
