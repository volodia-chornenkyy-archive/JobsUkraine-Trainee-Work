package entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.queries.CriteriaApi;
import entity.queries.JavaPersitenceQueryLang;
import entity.queries.Named;
import services.CarService;
import services.EmployeeService;
import services.LineService;
import services.PhoneService;
import services.PointService;

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

	private static void runBasicSQLQueries(PointService service) {

		System.out.println(SPACE + "\nSearch result: " + service.find(40L) + SPACE);
		System.out.println("Search result: " + service.find(300L) + SPACE);

		System.out.println(SPACE + "\nRemove result: " + service.remove(4L) + SPACE);

		System.out.println(SPACE + "\nUpdate result: " + service.update(1L, 100f, 100f) + SPACE);

		System.out.println(SPACE + "\nCheck if loaded: " + service.pointInitialized(1000L) + SPACE);
	}

	public static void main(String[] args) {

		/*
		 * persistence.xml should exist anyway, but custom properties can be set
		 * as second parameter to createEntityManagerFactory()
		 */
		// WARNING: new properties will overwrite existing
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaProvider",
				getCustomProperties("root", "admin", "testdb"));
		EntityManager em = emf.createEntityManager();

		PointService pointService = new PointService(em);
		LineService ls = new LineService(em);
		CarService cs = new CarService(em);
		PhoneService phoneService = new PhoneService(em);
		EmployeeService employeeService = new EmployeeService(em);
		Named namedQueries = new Named(em);

		// runBasicSQLQueries(pointService);

		// create points
		// ps.addPoints(15);

		// add points to line @OneToMany relations
		// ls.create("fourth", 5L, 10L);

		// select multiple object from tables
		// pointService.printListOfIdWithLine();

		// System.out.println(ls.getPoints("first"));

		// pointService.printByFigure(Figure.TRIANGLE);

		// pointService.printAll();

		// System.out.println(pointService.getAmount());

		// pointService.printByFilter(Figure.SQUARE, 20f, null, null);

		// check named queries from annotation
		// System.out.println(namedQueries.selectAllPoints());
		// System.out.println(namedQueries.selectFigure(Figure.SQUARE));

		// check named queries from orm.xml
		// System.out.println(namedQueries.selectLessThanId(5L));

		// JOIN example
		// pointService.printPointsWithLineInfo();
		// @OneToOne relations
		// cs.createManyCars(15);
		// cs.printCarsWithNumberInfo();

		// @OneToMany unidirectional relations
		long id = employeeService.create("Black", "Java", "Java", new Date());
		employeeService.add(id, phoneService.create("12345", "fixed"));
		employeeService.add(id, phoneService.create("54321", "mobile"));

		// Close the database connection:
		em.close();  
		emf.close();
	}
}
