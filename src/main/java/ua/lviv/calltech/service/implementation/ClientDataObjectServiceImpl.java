package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.Status;
import ua.lviv.calltech.repository.ClientDataObjectRepository;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.ResultService;
import ua.lviv.calltech.service.StatusService;

@Service
public class ClientDataObjectServiceImpl implements ClientDataObjectService{

	@Autowired
	private ClientDataObjectRepository clientDataObjectRepositiry;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private StatusService statusService;

	@Transactional
	public void save(ClientDataObject object, int projectId, int resultId) {
		Project project = projectService.findOne(projectId);
		Result result = resultService.findOne(resultId);
		Status status = statusService.findOne(Integer.parseInt(object.getStatus().getName()));
		object.setStatus(status);
		object.setProject(project);
		object.setResult(result);
		clientDataObjectRepositiry.save(object);
		result.setClient(object);
		resultService.save(result);
	}

	@Transactional
	public void save(ClientDataObject object) {
		Status status = statusService.findOne(Integer.parseInt(object.getStatus().getName()));
		// object return's with null field in object.getProject() so we finding needed project using result  
		Result result = resultService.findIdByClientId(object.getId());
		Project project = projectService.findOne(result.getProject().getId());
		object.setStatus(status);
		object.setProject(project);
		clientDataObjectRepositiry.save(object);
	}

	public List<SimpleClientObjectDTO> findAllByProjectIdWithResults(int projectId) {
		List<SimpleClientObjectDTO> object = clientDataObjectRepositiry.findSimpleClientsByProjectId(projectId);
		return object;
	}
	
	@Transactional
	public ClientDataObject findOneWithStatusAndProject(int clientId) {
		ClientDataObject client = clientDataObjectRepositiry.findOneWithStatusAndProject(clientId);
		return client;
	}
	
	

}
