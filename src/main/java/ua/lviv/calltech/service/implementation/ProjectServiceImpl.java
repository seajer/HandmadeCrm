package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.DTO.QuestionnaireDTO;
import ua.lviv.calltech.DTO.UserDTO;
import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.ProjectType;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.LanguageRepository;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.ProjectTypeRepository;
import ua.lviv.calltech.repository.QuestionnaireRepository;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private LanguageRepository langRepository;
	
	@Autowired
	private ProjectTypeRepository pTypeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
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

	@Transactional
	public ProjectDTO findDtoById(int projectId) {
		ProjectDTO project = projectRepository.findDtoById(projectId);
		if(project != null){
			List<UserDTO> projectUsers = userRepository.findAllDtoFromProject(projectId);
			QuestionnaireDTO questionnaire = questionnaireRepository.findDtoByProjectId(projectId);
			project.setUsers(projectUsers);
			project.setQuestionnaire(questionnaire);
		}
		return project;
	}
	
	@Transactional
	public void editProject(int projectId, String title, String company, int langId, int typeId, int[] usersId) {
		Project project = projectRepository.findOne(projectId);
		if(project != null){
			Language lang = langRepository.findOne(langId);
			ProjectType type = pTypeRepository.findOne(typeId);
			project.setTitle(title);
			project.setCompanyName(company);
			List<User> users = new ArrayList<User>();
			for (int i : usersId) {
				User u = userRepository.findOne(i);
				users.add(u);
			}
			project.setUsers(users);
			if(lang != null){
				project.setLanguage(lang);
			}
			if(type != null){
				project.setType(type);
			}
			projectRepository.save(project);
		}
	}

	public Project findOne(int projectId) {
		return projectRepository.findOne(projectId);
	}

	@Transactional
	public Project findOneWithType(int projectId) {
		Project project = projectRepository.findOneWithType(projectId);
		return project;
	}

	@Transactional
	public Integer findIdByResultId(int resultId) {
		Integer projectId = projectRepository.findIdByResultId(resultId); 
		return projectId;
	}

	public Project findByNaneAndCompany(String name, String company) {
		Project project = projectRepository.findByNameAndCompany(name, company);
		return project;
	}

}
