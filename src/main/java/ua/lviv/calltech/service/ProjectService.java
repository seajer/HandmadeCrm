package ua.lviv.calltech.service;

import java.util.List;
import java.util.Map;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.entity.Project;

public interface ProjectService {

	void addProject(String name, String company, int langId, int typeId);

	List<Project> findAll();
	
	List<Project> findAllWithLanguageAndType();

	void deleteProject(int projectId);

	ProjectDTO findDtoById(int projectId);

	void editProject(int projectId, String title, String company, int langId, int typeId, int[] usersId);
	
	Project findOne(int projectId);

	Project findOneWithType(int projectId);

	Integer findIdByResultId(int resultId);

	Project findByNaneAndCompany(String name, String company);
	
	void generateOutput(Map<Integer, String> map, int projectId);

}
