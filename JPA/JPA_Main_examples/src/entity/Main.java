package entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.queries.CriteriaApi;
import entity.queries.JavaPersitenceQueryLang;

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

	private static void runCriteriaQueries(EntityManager em) {
		CriteriaApi criteriaApi = new CriteriaApi(em);

		System.out.println("criteriaQuery select all: " + criteriaApi.selectAll() + SPACE);

		// don't work
		// System.out.println("criteriaQuery select squares: " +
		// criteriaApi.selectFigure(Figure.SQUARE) + SPACE);

		System.out.println("criteriaQuery count entities: " + criteriaApi.countAll() + SPACE);

		System.out.println(
				"criteriaQuery custom select: " + criteriaApi.filterPoints(Figure.SQUARE, 10f, null, null) + SPACE);
	}

	private static void runJPQLQueries(EntityManager em) {
		JavaPersitenceQueryLang jpql = new JavaPersitenceQueryLang(em);

		System.out.println("criteriaQuery select all: " + jpql.selectAll() + SPACE);

		System.out.println("criteriaQuery select squares: " + jpql.selectFigure(Figure.SQUARE) + SPACE);

		System.out.println("criteriaQuery count entities: " + jpql.countAll() + SPACE);

		System.out.println("criteriaQuery custom select: " + jpql.filterPoints(Figure.SQUARE, 10f, null, null) + SPACE);
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

		PointService ps = new PointService(em);
		LineService ls = new LineService(em);

		// create points
		// fillTableWithPoints(ps, 15, true);

		// add points to line @OneToMany relations
		// LineService ls = new LineService(em);
		// ls.create("first");
		// for (long i = 5; i < 10; i++) {
		// ls.addPoint(1L,i);
		// }

		// get all points in selected line
		// System.out.println(ls.getPoints(1L));

		// runBasics(ps);

		// Queries
		// runJPQLQueries(em);
		// runCriteriaQueries(em);

		// check named queries from annotation
		// TypedQuery<Point> tq1 = em.createNamedQuery("Point.getAll",
		// Point.class);
		// System.out.println(tq1.getResultList());

		// TypedQuery<Point> tq2 = em.createNamedQuery("Point.selectFigure",
		// Point.class);
		// System.out.println(tq2.setParameter("figure",
		// Figure.TRIANGLE).getResultList());

		// check named queries from orm.xml
		// TypedQuery<Point> tq3 = em.createNamedQuery("Point.selectLess",
		// Point.class);
		// System.out.println(tq3.setParameter("maxValue", 5L).getResultList());

		// @OneToOne relations
		// em.getTransaction().begin();
		// for (int i = 0; i < 15; i++) {
		// Car c = new Car();
		// c.setManufacturer("manufacturer" + i);
		// c.setNumber(new CarNumber(String.valueOf(i)));
		// em.persist(c);
		// }
		// em.getTransaction().commit();
		// TypedQuery<Car> tq4 = em.createQuery("select c from Car c, CarNumber
		// cn where c.number=cn.id ", Car.class);
		// System.out.println(tq4.getResultList());

		// JOIN example
		// TypedQuery<Point> tq4 = em.createQuery("select p from Line l join
		// l.points p", Point.class);
		// System.out.println(tq4.getResultList());

		// TypedQuery<Car> tq4 = em.createQuery("select c from Car c, CarNumber
		// cn where c.number=cn.id ", Car.class);
		// System.out.println(tq4.getResultList());

		// @OneToMany unidirectional relations
		// em.getTransaction().begin();
		//
		// Phone phone1 = new Phone();
		// phone1.setNumber("55555");
		// phone1.setType("fixed");
		// em.persist(phone1);
		//
		// Phone phone2 = new Phone();
		// phone2.setNumber("111-111");
		// phone2.setType("mobile");
		// em.persist(phone2);
		//
		// Employee employee = new Employee();
		// employee.setName("Jack");
		// employee.setSurname("Thomson");
		// employee.setTitle("QA Engineer");
		// employee.setCreated(new Date());
		// employee.addPhone(phone1);
		// employee.addPhone(phone2);
		//
		// em.persist(employee);
		//
		// long employeeId = employee.getId();
		//
		// em.getTransaction().commit();

		// em.getTransaction().begin();
		//
		// Employee dbEmployee = em.find(Employee.class, 1L);
		// System.out.println("dbEmployee " + dbEmployee);
		//
		// em.getTransaction().commit();

		// Close the database connection:
		em.close();
		emf.close();
	}
}
