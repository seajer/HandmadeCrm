package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;

public interface ClientDataObjectService {

	void save(ClientDataObject object);

	void save(ClientDataObject object, int projectId, int resultId);

	List<SimpleClientObjectDTO> findAllByProjectIdWithResults(int projectId);

	ClientDataObject findOneWithStatusAndProject(int clientId);

}
