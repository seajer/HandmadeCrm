package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Answer;
import ua.lviv.calltech.repository.AnswerRepository;
import ua.lviv.calltech.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private AnswerRepository answerRepository;
	
	public Answer findOne(int answerId) {
		Answer answer = answerRepository.findOne(answerId);
		return answer;
	}

	@Transactional
	public void save(Answer ansver) {
		answerRepository.save(ansver);
	}

	public List<Answer> findAllByResultId(int resultId) {
		List<Answer> answers = answerRepository.findAllByResultId(resultId);
		return answers;
	}

	@Transactional
	public void saveAll(List<Answer> answers) {
		if(answers.size() > 1){
			answerRepository.save(answers);
		}
	}

}
