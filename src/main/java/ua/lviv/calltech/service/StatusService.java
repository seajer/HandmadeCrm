package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Status;

public interface StatusService {

	List<Status> findAll();
	
	void setDefaultStatuses();

	Status findOne(int parseInt);

}
