package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @OneToOne example with Car.java

@Entity
public class CarNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String number;

	public CarNumber() {
		// TODO Auto-generated constructor stub
	}

	public CarNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CarNumber [id=" + id + ", number=" + number + "]";
	}

}
