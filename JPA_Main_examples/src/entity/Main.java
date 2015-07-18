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

		/* BASIC */

		// fillTableWithPoints(50, true);

		System.out.println("Search result: " + ps.find(40L));
		System.out.println("Search result: " + ps.find(300L));

		System.out.println("Remove result: " + ps.remove(4L));

		System.out.println("Update result: " + ps.update(1L, 100f, 100f));

		System.out.println("Check if loaded: " + ps.pointInitialized(1000L));

		/* QUERY */

		System.out.println("typedQuery select all: " + pq.selectAllTypedQuery());
		System.out.println("criteriaQuery select all: " + pq.selectAllCriteriaQuery());

		System.out.println("typedQuery select triangles: " + pq.selectFigureTypedQuery(Figure.TRIANGLE));
		System.out.println("criteriaQuery select squares: " + pq.selectFigureTypedQuery(Figure.SQUARE));

		// Close the database connection:
		em.close();
		emf.close();
	}
}
