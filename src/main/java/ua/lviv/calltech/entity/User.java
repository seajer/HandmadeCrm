package ua.lviv.calltech.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String fullName;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String phone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="users_projects", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="project_id"))
	private List<Project> projects;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="users_languages", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="lang_id"))
	private List<Language> language;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "user")
	private List<SingleResult> results;
	
	public User() {
	}
	
	public User(String fullName, String email, String phone) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Language> getLanguage() {
		return language;
	}
	public void setLanguage(List<Language> language) {
		this.language = language;
	}

	public List<SingleResult> getResults() {
		return results;
	}

	public void setResults(List<SingleResult> results) {
		this.results = results;
	}
	
	
}
