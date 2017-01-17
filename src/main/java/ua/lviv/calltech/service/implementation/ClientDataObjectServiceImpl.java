package ua.lviv.calltech.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;

@Service
public class ClientDataObjectServiceImpl implements ClientDataObjectService{

	@Autowired
	private ProjectService projectService;
	
	public void startPoll(int projectId) {
		Project project = projectService.findOne(projectId);
		if(project != null){
			
		}
	}
	

}
