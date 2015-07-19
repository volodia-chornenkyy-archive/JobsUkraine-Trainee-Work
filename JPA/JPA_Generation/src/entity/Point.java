package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the point database table.
 * 
 */

/*
 * To generate entity from table:
 * 1 convert project to JPA
 * 2 in context menu of project choose JPA Tools
 * 3 select Generate Entity from Table
 * 4 go through steps of master
 */
@Entity
@NamedQuery(name="Point.findAll", query="SELECT p FROM Point p")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private int alsoIgnored;

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

	public int getAlsoIgnored() {
		return this.alsoIgnored;
	}

	public void setAlsoIgnored(int alsoIgnored) {
		this.alsoIgnored = alsoIgnored;
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

}