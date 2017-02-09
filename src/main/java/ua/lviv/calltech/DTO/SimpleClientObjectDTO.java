package ua.lviv.calltech.DTO;

public class SimpleClientObjectDTO {
	
	private int id;
	private int resultId;
	private String phone;
	private String firstName;
	private String lastName;
	private String statusName;
	private String company;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public SimpleClientObjectDTO(int id, int resultId, String phone, String firstName, String lastName,
			String statusName, String company) {
		super();
		this.id = id;
		this.resultId = resultId;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.statusName = statusName;
		this.company = company;
	}
	
}
