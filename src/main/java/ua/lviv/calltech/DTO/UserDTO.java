package ua.lviv.calltech.DTO;

public class UserDTO {
	
	private int id;
	private String name;
	public UserDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public UserDTO() {
		super();
	}
	
}
