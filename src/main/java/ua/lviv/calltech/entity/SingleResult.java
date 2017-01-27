package ua.lviv.calltech.entity;

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

@Entity
public class SingleResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String customAnswer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="result_answer", inverseJoinColumns=@JoinColumn(name="answer_id"), joinColumns=@JoinColumn(name="result_id"))
	private List<Answer> answers;
	@ManyToOne(fetch = FetchType.LAZY)
	private ClientDataObject client;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
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
	

	public String getCustomAnswer() {
		return customAnswer;
	}

	public void setCustomAnswer(String customAnswer) {
		this.customAnswer = customAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SingleResult() {
		super();
	}

	public SingleResult(Project project, User user) {
		super();
		this.project = project;
		this.user = user;
	}
	
}
