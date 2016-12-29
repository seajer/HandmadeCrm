package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.ProjectType;

public interface ProjectTypeService {

	void addNewPType(String type);

	List<ProjectType> findAll();

	void deletePType(int projectTypeId);

}
