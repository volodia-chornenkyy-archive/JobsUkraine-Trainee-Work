package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

// @OneToOne example with CarNumber.java

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String manufacturer;

	@OneToOne
	@MapsId
	private CarNumber number;

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public CarNumber getNumber() {
		return number;
	}

	public void setNumber(CarNumber number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", manufacturer=" + manufacturer + ", number=" + number + "]\n";
	}

}