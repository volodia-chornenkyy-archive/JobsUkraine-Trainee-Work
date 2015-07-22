package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String number;

	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String phoneNo) {
		this.number = phoneNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String phoneType) {
		this.type = phoneType;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", type=" + type + "]";
	}
}
