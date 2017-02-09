package ua.lviv.calltech.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;

public interface ClientDataObjectService {
	
	String[] clientDataObjectParams = {"Name", "Surname", "Age", "Position", "Company name", "Industry", "Workers count",
			"Years earning", "Description", "Adress", "Country", "Phone", "Email", "Site adress", "Comments"};

	void save(ClientDataObject object);

	void save(ClientDataObject object, int projectId, int resultId);

	ClientDataObject findOneWithStatusAndProject(int clientId);
	
	void setParameter(ClientDataObject client, Cell cell, Map<Integer, String> rules);
	
	List<ClientDataObject> readFromExcel(Map<Integer, String> map, String fileName, String fileType);

	File saveFile(MultipartFile file);

	ClientDataObject findOneWithResults(int clientId);

	void createOne(String phone, String company);

	ClientDataObject findOneByPhoneAndCompany(String phone, String company);

	List<SimpleClientObjectDTO> findAllByProjectId(int projectId);

	int findIdByResultId(int resultId);

}
