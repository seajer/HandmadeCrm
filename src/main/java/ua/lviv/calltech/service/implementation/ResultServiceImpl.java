package ua.lviv.calltech.service.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.repository.ResultRepository;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ClientDataObjectService cdoService;

	public List<Result> findByProjectPhoneAndCompany(int projectId, String phone, String company) {
		System.out.println("id = " + projectId + " phone = " + phone + " company = " + company);
		List<Result> result = resultRepository.findByPhoneAndCompany(projectId, phone, company); 
		return result;
	}

	@Transactional
	public void createOne(String phone, String company, int projectId) {
		ClientDataObject cdo = cdoService.findOneByPhoneAndCompany(phone, company); 
		Project project = projectService.findOne(projectId);
		Result result = new Result();
		result.setClient(cdo);
		result.setProject(project);
		resultRepository.save(result);
	}

	@Transactional
	public Integer findQuestionnaireIdByResultId(int resultId) {
		return null;
	}
}
