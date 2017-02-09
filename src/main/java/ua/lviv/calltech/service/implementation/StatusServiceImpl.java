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

	public Status findOne(int statusId) {
		Status status = statusRepository.findOne(statusId);
		return status;
	}

	@Transactional
	public void createStatuses() {
		Status notStarted = new Status("Pending");
		statusRepository.save(notStarted);
		Status done = new Status("Done");
		statusRepository.save(done);
		Status rejected = new Status("Rejected");
		statusRepository.save(rejected);
		Status recall = new Status("Recall");
		statusRepository.save(recall);
	}

	public Status findDefault() {
		return statusRepository.findByName("Pending");
	}

	public Status findByResultId(int resultId) {
		return statusRepository.findByResultId(resultId);
	}

}
