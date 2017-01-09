package ua.lviv.calltech.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String text;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "type")
	private List<Question> questions;
	
	public QuestionType(String text) {
		super();
		this.text = text;
	}

	public QuestionType() {
	}

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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
}
