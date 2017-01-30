package ua.lviv.calltech.service;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

import ua.lviv.calltech.entity.ClientDataObject;

public interface ClientDataObjectService {
	
	String[] clientDataObjectParams = {"Name", "Surname", "Age", "Position", "Company name", "Industry", "Workers count",
			"Years earning", "Description", "Adress", "Country", "Phone", "Email", "Site adress", "Comments"};

	void save(ClientDataObject object);

	void save(ClientDataObject object, int projectId, int resultId);

	//List<SimpleClientObjectDTO> findAllByProjectIdWithResults(int projectId);

	ClientDataObject findOneWithStatusAndProject(int clientId);
	
	void setParameter(ClientDataObject client, Cell cell, Map<Integer, String> rules);
	
	void readFromExcel(Map<Integer, String> map);

}
