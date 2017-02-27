package ua.lviv.calltech.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.entity.Status;
import ua.lviv.calltech.repository.ResultRepository;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.ResultService;
import ua.lviv.calltech.service.SingleResultService;
import ua.lviv.calltech.service.StatusService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ClientDataObjectService cdoService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private SingleResultService singleResultService;

	public List<Result> findByProjectPhoneAndCompany(int projectId, String phone, String company) {
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

	@Transactional
	public void createResultsForCDOs(List<ClientDataObject> clients, int projectId) {
		Project project = projectService.findOne(projectId);
		for (ClientDataObject cdo : clients) {
			Status st = statusService.findDefault();
			Result result = new Result();
			result.setProject(project);
			result.setClient(cdo);
			result.setStatus(st);
			resultRepository.save(result);
		}
	}

	public Result findOne(int resultId) {
		return resultRepository.findOne(resultId);
	}

	@Transactional
	public void changeStatus(int statusId, int resultId, String recall) {
		Status status = statusService.findOne(statusId);
		Result result = resultRepository.findOne(resultId);
		if(status != null && result != null){
			result.setStatus(status);
			if(recall != null) result.setRecallTime(LocalDateTime.now());
			resultRepository.save(result);
		}
	}

	public List<Result> findAllFinishedByProject(int projectId) {
		List<Result> result = resultRepository.findAllFinishedByProjectId(projectId);
		for (Result result2 : result) {
			List<SingleResult> sr = singleResultService.findAllByResultId(result2.getId());
			result2.setResults(sr);
		}
		return result;
	}
}
