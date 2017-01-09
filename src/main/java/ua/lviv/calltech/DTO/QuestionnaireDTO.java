package ua.lviv.calltech.DTO;

import java.util.List;

public class QuestionnaireDTO {
	
	private int id;
	private String text;
	private List<QuestionDTO> questions;
	
	public QuestionnaireDTO(int questionnaireId, String text) {
		super();
		this.id = questionnaireId;
		this.text = text;
	}
	public QuestionnaireDTO() {
		super();
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
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
}
