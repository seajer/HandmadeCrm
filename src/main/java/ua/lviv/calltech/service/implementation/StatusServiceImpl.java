package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.Status;
import ua.lviv.calltech.repository.StatusRepository;
import ua.lviv.calltech.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	private StatusRepository statusRepository;

	public List<Status> findAll() {
		List<Status> statuses = statusRepository.findAll();
		return statuses;
	}

	@Transactional
	public void setDefaultStatuses() {
		Status done = new Status("Done");
		statusRepository.save(done);
		Status busy = new Status("Busy");
		statusRepository.save(busy);
		Status rejected = new Status("Rejected");
		statusRepository.save(rejected);
	}

	public Status findOne(int statusId) {
		Status status = statusRepository.findOne(statusId);
		return status;
	}

}
