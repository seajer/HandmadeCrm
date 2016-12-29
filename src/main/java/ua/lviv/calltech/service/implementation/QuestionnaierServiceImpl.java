package ua.lviv.calltech.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Questionnaire;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.QuestionnaireRepository;
import ua.lviv.calltech.service.QuestionnaierService;

@Service
public class QuestionnaierServiceImpl implements QuestionnaierService{

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
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
	public Questionnaire findByIdWithQuestions(int id) {
		return questionnaireRepository.findByIdWithQuestions(id);
	}

}
