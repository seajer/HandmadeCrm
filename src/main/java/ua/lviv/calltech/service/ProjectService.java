package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Project;

public interface ProjectService {

	void addProject(String name, String company, int langId, int typeId);

	List<Project> findAll();
	
	List<Project> findAllWithLanguageAndType();

	void deleteProject(int projectId);

}
