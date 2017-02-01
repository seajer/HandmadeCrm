package ua.lviv.calltech.service;

import java.util.List;
import java.util.Map;

import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;

public interface ResultService {

	List<Result> findByProjectPhoneAndCompany(int projectId, String phone, String company);

	void createOne(String phone, String company, int projectId);

}
