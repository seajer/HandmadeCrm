package ua.lviv.calltech.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ClientDataObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String age;
	@Column
	private String position;
	@Column
	private String companyName;
	@Column
	private String industry;
	@Column
	private String workersCount;
	@Column
	private String yearEarning;
	@Column(columnDefinition="varchar(1000)")
	private String description;
	@Column(columnDefinition="varchar(1000)")
	private String adress;
	@Column
	private String country;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String site;
	@Column(columnDefinition="varchar(1000)")
	private String comment;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<Result> results;
	
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public String getWorkersCount() {
		return workersCount;
	}

	public void setWorkersCount(String workersCount) {
		this.workersCount = workersCount;
	}

	public String getYearEarning() {
		return yearEarning;
	}

	public void setYearEarning(String yearEarning) {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ClientDataObject [name=" + name 
				+ ", surname=" + surname 
//				+ ", age=" + age 
//				+ ", position=" + position
				+ ", companyName=" + companyName 
//				+ ", industry=" + industry 
//				+ ", workersCount=" + workersCount
//				+ ", yearEarning=" + yearEarning 
//				+ ", description=" + description 
//				+ ", adress=" + adress 
//				+ ", country=" + country 
				+ ", phone=" + phone 
//				+ ", email=" + email 
//				+ ", site=" + site 
				+ ", comment=" + comment + "]";
	}
}
