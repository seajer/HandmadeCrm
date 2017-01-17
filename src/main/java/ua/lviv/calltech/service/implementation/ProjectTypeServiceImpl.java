package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.entity.ProjectType;
import ua.lviv.calltech.repository.ProjectTypeRepository;
import ua.lviv.calltech.service.ProjectTypeService;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService{

	@Autowired
	private ProjectTypeRepository pTypeRepository;
	
	@Transactional
	public void addNewPType(String type) {
		ProjectType pt = new ProjectType(type);
		pTypeRepository.save(pt);
	}

	public List<ProjectType> findAll() {
		return pTypeRepository.findAll();
	}

	@Transactional
	public void deletePType(int projectTypeId) {
		ProjectType pt = pTypeRepository.findOne(projectTypeId);
		if(pt != null) {
			pTypeRepository.delete(projectTypeId);
		}
	}

	public List<ProjectType> typesExceptProjects(ProjectDTO project) {
		List<ProjectType> all = findAll();
		ProjectType projectsType = pTypeRepository.findByProjectsId(project.getId());
		project.setType(projectsType);
		all.remove(projectsType);
		return all;
	}

	@Transactional
	public void edit(int projectTypeId, String name) {
		ProjectType pType = pTypeRepository.findOne(projectTypeId);
		if(pType != null) {
			pType.setName(name);
			pTypeRepository.save(pType);
		}
	}

	@Transactional
	public void createProjectTypes() {
		ProjectType pollWithDB = new ProjectType("Poll with customers DB");
		pTypeRepository.save(pollWithDB);
		ProjectType pollWithoutDB = new ProjectType("Poll using yellowpages");
		pTypeRepository.save(pollWithoutDB);
		ProjectType dBValidation = new ProjectType("Validation customers DB");
		pTypeRepository.save(dBValidation);
	}

}
