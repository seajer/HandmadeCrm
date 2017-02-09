package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Status;

public interface StatusService {

	List<Status> findAll();

	Status findOne(int parseInt);

	void createStatuses();

	Status findDefault();

	Status findByResultId(int resultId);

}
