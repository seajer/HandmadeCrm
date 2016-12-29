package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
