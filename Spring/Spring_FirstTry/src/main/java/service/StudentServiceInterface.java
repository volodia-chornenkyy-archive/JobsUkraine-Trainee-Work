package main.java.service;

import main.java.beans.Student;

public interface StudentServiceInterface {
	public Student getRandom();
	public Student getById(int id);
	public void save(Student person);
}
