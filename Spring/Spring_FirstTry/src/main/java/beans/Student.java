package main.java.beans;

public class Student {

	private String name;
	private String group;
	private int id;

	public Student() {
		this.name = "defaultName";
		this.group = "defaultGroup";
		this.id = -1;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", group=" + group + ", id=" + id + "]";
	}
	
}
