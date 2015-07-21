package entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import myutil.MyTimer;

public class Main {

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

	private static void fillTableWithPoints(PointService service, int amount, boolean log) {
		// Store points in the database:
		for (int i = 0; i < amount; i++) {
			service.create(i, i * i);
			if (log)
				System.out.printf("created %d from %d\n", i, amount);
		}
	}

	private static void runBasics(PointService service) {

		System.out.println(SPACE + "\nSearch result: " + service.find(40L) + SPACE);
		System.out.println("Search result: " + service.find(300L) + SPACE);

		System.out.println(SPACE + "\nRemove result: " + service.remove(4L) + SPACE);

		System.out.println(SPACE + "\nUpdate result: " + service.update(1L, 100f, 100f) + SPACE);

		System.out.println(SPACE + "\nCheck if loaded: " + service.pointInitialized(1000L) + SPACE);
	}

	private static void runQueries(EntityManager em) {
		PointQuery pq = new PointQuery(em);
		MyTimer timer = new MyTimer();

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

		PointService ps = new PointService(em);

		// fillTableWithPoints(ps, 50, true);

		runBasics(ps);

		runQueries(em);

		// Close the database connection:
		em.close();
		emf.close();
	}
}
