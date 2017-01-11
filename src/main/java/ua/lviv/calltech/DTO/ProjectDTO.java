package ua.lviv.calltech.DTO;

import java.util.List;

import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.ProjectType;

public class ProjectDTO {
	
	private int id;
	private String companyName;
	private String title;
	private ProjectType type;
	private Language language;
	private QuestionnaireDTO questionnaire;
	private List<UserDTO> users;
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
	
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public QuestionnaireDTO getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(QuestionnaireDTO questionnaire) {
		this.questionnaire = questionnaire;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public ProjectDTO() {
		super();
	}
	public ProjectDTO(int id, String companyName, String title) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.title = title;
	}
	public ProjectDTO(int id, String companyName, String title, ProjectType type, Language language) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.title = title;
		this.type = type;
		this.language = language;
	}
}
