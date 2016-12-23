package ua.lviv.calltech.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String companyName;
	@Column
	private String title;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private ProjectType type;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	private List<User> users;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lang_id")
	private Language language;
	@OneToOne
	@JoinColumn(name = "questionnaire_id")
	private Questionnaire questionnaire;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private List<ClientDataObject> clientData;
	
	public Project() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProjectType getType() {
		return type;
	}

	public void setType(ProjectType type) {
		this.type = type;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<ClientDataObject> getClientData() {
		return clientData;
	}

	public void setClientData(List<ClientDataObject> clientData) {
		this.clientData = clientData;
	}
	
	
}
