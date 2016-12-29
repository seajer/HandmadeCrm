package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.ProjectType;
import ua.lviv.calltech.repository.LanguageRepository;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.ProjectTypeRepository;
import ua.lviv.calltech.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private LanguageRepository langRepository;
	
	@Autowired
	private ProjectTypeRepository pTypeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Transactional
	public void addProject(String title, String company, int langId, int typeId) {
		Project project = new Project(company, title);
		Language lang = langRepository.findOne(langId);
		ProjectType pt = pTypeRepository.findOne(typeId);
		project.setLanguage(lang);
		project.setType(pt);
		projectRepository.save(project);
	}

	@Transactional
	public List<Project> findAllWithLanguageAndType() {
		return projectRepository.findAllWithLanguageAndType();
	}

	@Transactional
	public void deleteProject(int projectId) {
		if(projectRepository.findOne(projectId) != null){
			projectRepository.delete(projectId);
		}
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

}
