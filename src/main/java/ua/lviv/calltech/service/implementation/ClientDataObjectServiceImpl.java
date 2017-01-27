package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.repository.ClientDataObjectRepository;
import ua.lviv.calltech.service.ClientDataObjectService;

@Service
public class ClientDataObjectServiceImpl implements ClientDataObjectService{

	@Autowired
	private ClientDataObjectRepository clientDataObjectRepositiry;
	
//	@Autowired
//	private ProjectService projectService;
//	
//	@Autowired
//	private ResultService resultService;
//	
//	@Autowired
//	private StatusService statusService;

	@Transactional
	public void save(ClientDataObject object, int projectId, int resultId) {
		// TODO: rewrite this method
	}

	@Transactional
	public void save(ClientDataObject object) {
		// TODO: rewrite this method
	}

	public List<SimpleClientObjectDTO> findAllByProjectIdWithResults(int projectId) {
		List<SimpleClientObjectDTO> object = clientDataObjectRepositiry.findSimpleClientsByProjectId(projectId);
		return object;
	}
	
	@Transactional
	public ClientDataObject findOneWithStatusAndProject(int clientId) {
//		ClientDataObject client = clientDataObjectRepositiry.findOneWithStatusAndProject(clientId);
		return null;
	}
	
	

}
