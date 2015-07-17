package entity;

import javax.persistence.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		String user = "root";
		String password = "admin";

		String dbName = "testdb";
				
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", user);
		properties.put("javax.persistence.jdbc.password", password);
		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		//properties.put("javax.persistence.jdbc.url", "org.apache.derby.jdbc.EmbeddedDriver");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc:mysql://localhost:3306/" + dbName,
				properties);
		EntityManager em = emf.createEntityManager();

		// Store 1000 Point objects in the database:
		em.getTransaction().begin();
		for (int i = 0; i < 1000; i++) {
			Point p = new Point(i, i);
			em.persist(p);
		}
		em.getTransaction().commit();

		// Find the number of Point objects in the database:
		Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
		System.out.println("Total Points: " + q1.getSingleResult());

		// Find the average X value:
		Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
		System.out.println("Average X: " + q2.getSingleResult());

		// Retrieve all the Point objects from the database:
		TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
		List<Point> results = query.getResultList();
		for (Point p : results) {
			System.out.println(p);
		}

		// Close the database connection:
		em.close();
		emf.close();
	}
}
