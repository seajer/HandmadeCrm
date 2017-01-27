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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientDataObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private int age;
	@Column
	private String position;
	@Column
	private String companyName;
	@Column
	private String industry;
	@Column
	private int workersCount;
	@Column
	private int yearEarning;
	@Column
	private String description;
	@Column
	private String adress;
	@Column
	private String country;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String site;
	@Column
	private String comment;
	@Column
	private LocalDateTime recallTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="client_projects", inverseJoinColumns=@JoinColumn(name="project_id"), joinColumns=@JoinColumn(name="client_id"))
	private List<Project> projects;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<SingleResult> result;
	
	public ClientDataObject() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getWorkersCount() {
		return workersCount;
	}

	public void setWorkersCount(int workersCount) {
		this.workersCount = workersCount;
	}

	public int getYearEarning() {
		return yearEarning;
	}

	public void setYearEarning(int yearEarning) {
		this.yearEarning = yearEarning;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getRecallTime() {
		return recallTime;
	}

	public void setRecallTime(LocalDateTime recallTime) {
		this.recallTime = recallTime;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<SingleResult> getResult() {
		return result;
	}

	public void setResult(List<SingleResult> result) {
		this.result = result;
	}
	
	
}
