package entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import myutil.MyTimer;

public class Main {

	private static PointService ps;

	private static MyTimer timer;
	private static final String SPACE = "\n---------------------";

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
	
	private static void runQueries(EntityManager em){
		/* QUERY */
		private PointQuery pq = new PointQuery(em);
		timer = new MyTimer();
		
		timer.start();
		System.out.println(
				SPACE + "\ntypedQuery select all: " + pq.selectAllPointsJPQL() + "\ntime: " + timer.stop() + SPACE);
		timer.start();
		System.out.println(
				"criteriaQuery select all: " + pq.selectAllPointsCriteria() + "\ntime: " + timer.stop() + SPACE);

		timer.start();
		System.out.println(SPACE + "\ntypedQuery select triangles: " + pq.selectFigureJPQL(Figure.TRIANGLE) + "\ntime: "
				+ timer.stop() + SPACE);
		timer.start();
		System.out.println("criteriaQuery select squares: " + pq.selectFigureJPQL(Figure.SQUARE) + "\ntime: "
				+ timer.stop() + SPACE);

		timer.start();
		System.out.println(
				SPACE + "\ntypedQuery count entities: " + pq.countAllPointsJPQL() + "\ntime: " + timer.stop() + SPACE);
		timer.start();
		System.out.println(
				"criteriaQuery count entities: " + pq.countAllPointsCriteria() + "\ntime: " + timer.stop() + SPACE);

		timer.start();
		System.out.println(SPACE + "\ntypedQuery custom select: " + pq.filterPointsJPQL(Figure.SQUARE, 10f, null, null)
				+ "\ntime: " + timer.stop() + SPACE);
		timer.start();
		System.out.println("criteriaQuery custom select: " + pq.filterPointsCriteria(Figure.SQUARE, 10f, null, null)
				+ "\ntime: " + timer.stop() + SPACE);
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
		

		/* BASIC */

		// fillTableWithPoints(50, true);

		System.out.println(SPACE + "\nSearch result: " + ps.find(40L) + SPACE);
		System.out.println("Search result: " + ps.find(300L) + SPACE);

		System.out.println(SPACE + "\nRemove result: " + ps.remove(4L) + SPACE);

		System.out.println(SPACE + "\nUpdate result: " + ps.update(1L, 100f, 100f) + SPACE);

		System.out.println(SPACE + "\nCheck if loaded: " + ps.pointInitialized(1000L) + SPACE);

		runQueries();

		// Close the database connection:
		em.close();
		emf.close();
	}
}
