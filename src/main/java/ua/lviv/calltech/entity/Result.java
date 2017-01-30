package ua.lviv.calltech.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private LocalDateTime recallTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "totalResult")
	private List<SingleResult> results;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private ClientDataObject client;

	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRecallTime() {
		return recallTime;
	}

	public void setRecallTime(LocalDateTime recallTime) {
		this.recallTime = recallTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<SingleResult> getResults() {
		return results;
	}

	public void setResults(List<SingleResult> results) {
		this.results = results;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ClientDataObject getClient() {
		return client;
	}

	public void setClient(ClientDataObject client) {
		this.client = client;
	}
	
}
