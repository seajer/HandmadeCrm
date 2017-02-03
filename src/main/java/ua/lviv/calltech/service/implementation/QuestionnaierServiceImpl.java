package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.QuestionDTO;
import ua.lviv.calltech.DTO.QuestionnaireDTO;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Questionnaire;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.QuestionRepository;
import ua.lviv.calltech.repository.QuestionnaireRepository;
import ua.lviv.calltech.service.QuestionnaierService;

@Service
public class QuestionnaierServiceImpl implements QuestionnaierService{

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@Autowired
	private QuestionRepository questionRepository;
	
	@Transactional
	public void addQuestionnaire(String description, int projectId) {
		Questionnaire quest = new Questionnaire(description);
		Project project = projectRepository.findOne(projectId);
		if(project != null) {
			quest.setProject(project);
			project.setQuestionnaire(quest);
			projectRepository.save(project);
		}
		questionnaireRepository.save(quest);
	}

	@Transactional
	public Questionnaire findByIdWithQuestions(int id, boolean isVisible) {
		Questionnaire q = questionnaireRepository.findByIdWithQuestions(id);
		return q;
	}

	public Questionnaire findById(int questionnaireId) {
		return questionnaireRepository.findOne(questionnaireId);
	}

	public List<Questionnaire> findAll() {
		return questionnaireRepository.findAll();
	}

	@Transactional
	public QuestionnaireDTO findDtoByIdWithQuestionsAndVisible(int id, boolean isVisible) {
		QuestionnaireDTO q = questionnaireRepository.findDtoByIdAndVisible(id);
		List<QuestionDTO> questions = questionRepository.findDTOWithVisible(id, isVisible);
		q.setQuestions(questions);
		return q;
	}
	
	public int findIdByQuestionId(int questionId) {
		return questionnaireRepository.fibdIbByQuestionId(questionId);
	}

	@Transactional
	public void editQUestionnaire(int questionnaireId, String description) {
		Questionnaire q = questionnaireRepository.findOne(questionnaireId);
		if(q != null){
			q.setDescription(description);
			questionnaireRepository.save(q);
		}
	}

	@Transactional
	public int findByDescriptionAndProject(String description, int projectId) {
		Integer questionnaireId = questionnaireRepository.findByDescriptionAndProjectId(description, projectId);
		return questionnaireId;
	}

}
