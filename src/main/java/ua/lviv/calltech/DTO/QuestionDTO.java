package ua.lviv.calltech.DTO;

public class QuestionDTO {
	
	private int questionId;
	private String questionText;
	
	public QuestionDTO(int questionId, String questionText) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public QuestionDTO() {
		super();
	}
	
}
