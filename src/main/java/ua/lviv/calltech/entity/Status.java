package ua.lviv.calltech.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	private List<ClientDataObject> objects;
	
	public Status() {
	}

	public Status(String name) {
		super();
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

	public List<ClientDataObject> getObjects() {
		return objects;
	}

	public void setObjects(List<ClientDataObject> objects) {
		this.objects = objects;
	}
	
}
