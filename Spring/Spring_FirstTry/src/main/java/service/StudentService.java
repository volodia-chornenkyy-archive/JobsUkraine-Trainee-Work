package main.java.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import main.java.beans.Student;

@Service
public class StudentService implements StudentServiceInterface {

	String[] names = { "Nikolaus Otto", "Robert Ford", "Gottlieb Daimler", "Lt. General Masaharu Homma" };

	@Override
	public Student getRandom() {
		Student s = new Student();
		s.setName(randomName());
		s.setId(randomId());
		return s;
	}

	@Override
	public Student getById(int id) {
		Student s = new Student();
		s.setName(names[id]);
		s.setId(id);
		return s;
	}

	@Override
	public void save(Student student) {
		// Save person to database ...
	}

	private Integer randomId() {
		Random random = new Random();
		return 10 + random.nextInt(100);
	}

	private String randomName() {
		Random random = new Random();
		return names[random.nextInt(names.length)];
	}

}
