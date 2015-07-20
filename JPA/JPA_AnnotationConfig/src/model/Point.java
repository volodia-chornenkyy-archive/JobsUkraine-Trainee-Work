package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the point database table.
 * 
 */
@Entity
@Table(name="Points")
@NamedQuery(name="Point.findAll", query="SELECT p FROM Point p")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String figure;

	private float x;

	private float y;

	private float z;

	public Point() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFigure() {
		return this.figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return this.z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", figure=" + figure + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
}