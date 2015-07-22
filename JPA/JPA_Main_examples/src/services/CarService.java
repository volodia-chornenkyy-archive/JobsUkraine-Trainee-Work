package services;

import javax.persistence.EntityManager;

import entity.Car;
import entity.CarNumber;
import entity.queries.JavaPersitenceQueryLang;

public class CarService {

	private EntityManager em;
	private JavaPersitenceQueryLang jpql;

	public CarService(EntityManager em) {
		this.em = em;
		jpql = new JavaPersitenceQueryLang(em);
	}

	public void createManyCars(int amount) {
		em.getTransaction().begin();
		for (int i = 0; i < amount; i++) {
			Car c = new Car();
			c.setManufacturer("manufacturer" + i);
			c.setNumber(new CarNumber(String.valueOf(i)));
			em.persist(c);
		}
		em.getTransaction().commit();
	}
	
	public void printCarsWithNumberInfo(){
		System.out.println(jpql.joinUsingWhere());
	}

}
