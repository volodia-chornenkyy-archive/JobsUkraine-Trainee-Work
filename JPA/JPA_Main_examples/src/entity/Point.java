package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Point")
// @Access(AccessType.PROPERTY) // need setters&getters for all columns
@NamedQueries({ @NamedQuery(name = "Point.getAll", query = "select p from Point p"),
		@NamedQuery(name = "Point.selectFigure", query = "select p from Point p where p.thirdDimention.figure = :figure") })
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private float x;
	private float y;

	@ManyToOne
	private Line line;

	@Embedded
	private D3 thirdDimention;

	@Transient
	private int ignoredValue;
	transient int wontExcistInDatabase;

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

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return String.format("Point(%.2f, %.2f, %s, Line(%s))\n", this.getX(), this.getY(), this.thirdDimention,
				(this.line == null) ? "null" : this.line.getName());
	}
}