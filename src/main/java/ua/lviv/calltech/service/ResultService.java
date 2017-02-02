package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Result;

public interface ResultService {

	List<Result> findByProjectPhoneAndCompany(int projectId, String phone, String company);

	void createOne(String phone, String company, int projectId);

	void createResultsForCDOs(List<ClientDataObject> clients, int projectId);

}
