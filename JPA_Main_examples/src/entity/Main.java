package entity;

import javax.persistence.*;
import java.util.*;

public class Main {

	private static PointService ps;
	private static PointQuery pq;

	private static Map<String, String> getCustomProperties(String user, String password, String dbName) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost/" + dbName);
		properties.put("javax.persistence.jdbc.user", user);
		properties.put("javax.persistence.jdbc.password", password);
		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	private static void fillTableWithPoints(int amount, boolean log) {
		// Store points in the database:
		for (int i = 0; i < amount; i++) {
			ps.create(i, i * i);
			if (log)
				System.out.printf("created %d from %d\n", i, amount);
		}
	}

	public static void main(String[] args) {

		/*
		 * persistence.xml should exist anyway, but custom properties can be set
		 * as second parameter to createEntityManagerFactory()
		 */
		// WARNING: new properties will overwrite existing
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaProvider",
				getCustomProperties("root", "root", "testdb"));
		EntityManager em = emf.createEntityManager();

		ps = new PointService(em);
		pq = new PointQuery(em);

		fillTableWithPoints(1000, true);

		System.out.println("Search result: " + ps.find(100L));
		System.out.println("Search result: " + ps.find(300L));

		System.out.println("Remove result: " + ps.remove(4L));

		System.out.println("Update result: " + ps.update(1L, 100f, 100f));

		System.out.println("Check if loaded: " + ps.pointInitialized(1000L));

		// // Find the number of Point objects in the database:
		// Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
		// System.out.println("Total Points: " + q1.getSingleResult());
		//
		// // Find the average X value:
		// Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
		// System.out.println("Average X: " + q2.getSingleResult());
		//
		// // Retrieve all the Point objects from the database:
		// TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p",
		// Point.class);
		// List<Point> results = query.getResultList();
		// for (Point p : results) {
		// System.out.println(p);
		// }

		// Close the database connection:
		em.close();
		emf.close();
	}
}
