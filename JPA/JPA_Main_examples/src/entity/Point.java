package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Point")
// @Access(AccessType.PROPERTY) // need setters&getters for all columns
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private float x;
	private float y;

	@Embedded
	private D3 thirdDimention;

	@Transient
	private int ignoredValue;
	transient int wontExcistInDatabase;
	final int alsoIgnored = 0;
	// static int andThisToo = 0; // added as column but shouldn't

	public Point() {
	}

	Point(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	public Long getId() {
		return id;
	}

	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public D3 getThirdDimention() {
		return thirdDimention;
	}

	public void setThirdDimention(D3 thirdDimention) {
		this.thirdDimention = thirdDimention;
	}

	@Override
	public String toString() {
		return String.format("Point(%.2f, %.2f, %s)", this.getX(), this.getY(), this.thirdDimention);
	}
}