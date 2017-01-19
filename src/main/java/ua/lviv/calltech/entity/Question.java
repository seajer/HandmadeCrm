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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String text;
	@Column
	private boolean isVisible = true;
	@Column
	private String recomendations;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "question")
	private List<Answer> answers;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private QuestionType type;
	@ManyToOne(fetch = FetchType.LAZY)
	private Questionnaire questionnaire;
	@ManyToOne
	private Question table;
	@OneToMany(mappedBy="table")
	private List<Question> tableQuestions;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRecomendations() {
		return recomendations;
	}

	public void setRecomendations(String recomendations) {
		this.recomendations = recomendations;
	}
	
	public QuestionType getType() {
		return type;
	}

	public Question getTable() {
		return table;
	}

	public void setTable(Question table) {
		this.table = table;
	}

	public List<Question> getTableQuestions() {
		return tableQuestions;
	}

	public void setTableQuestions(List<Question> tableQuestions) {
		this.tableQuestions = tableQuestions;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Question() {
		this.isVisible = true;
	}

	public Question(String text, String recomendations, QuestionType type, Questionnaire questionnaire, boolean visible) {
		super();
		this.text = text;
		this.recomendations = recomendations;
		this.type = type;
		this.questionnaire = questionnaire;
		this.isVisible = visible;
	}

	public Question(String text) {
		super();
		this.text = text;
	}
	
}
