package services;

import java.util.Date;

import javax.persistence.EntityManager;

import entity.Employee;
import entity.Phone;
import entity.queries.JavaPersitenceQueryLang;

public class EmployeeService {

	private EntityManager em;
	private JavaPersitenceQueryLang jpql;

	public EmployeeService(EntityManager em) {
		this.em = em;
		jpql = new JavaPersitenceQueryLang(em);
	}

	public long create(String name, String surname, String title, Date created) {
		try {
			em.getTransaction().begin();
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSurname(surname);
			emp.setTitle(title);
			emp.setCreated(created);
			em.persist(emp);
			em.getTransaction().commit();
			return emp.getId();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	public void add(Object id, Phone p) {
		em.find(Employee.class, (Long) id).addPhone(p);
	}

	public Employee find(Object id) {
		return em.find(Employee.class, (Long) id);
	}

}
