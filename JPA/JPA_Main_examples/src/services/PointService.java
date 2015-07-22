package services;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import entity.D3;
import entity.Figure;
import entity.Line;
import entity.Point;
import entity.queries.CriteriaApi;
import entity.queries.JavaPersitenceQueryLang;

public class PointService {

	private EntityManager em;
	private JavaPersitenceQueryLang jpql;
	private CriteriaApi criteria;

	public PointService(EntityManager em) {
		this.em = em;
		jpql = new JavaPersitenceQueryLang(em);
		criteria = new CriteriaApi(em);
	}

	public Point create(float x, float y) {
		Point p;
		try {
			em.getTransaction().begin();
			p = new Point(x, y);
			p.setThirdDimention(new D3(x + 1, (Math.round(x) % 2) == 0 ? Figure.SQUARE : Figure.TRIANGLE));
			em.persist(p);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		return p;
	}

	public Point find(Object id) {
		return em.find(Point.class, id);
	}

	public Point remove(Object id) {
		Point p;
		try {
			em.getTransaction().begin();
			p = find(id);
			if (p != null) {
				em.remove(p);
			}
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		return p;
	}

	public Point update(Object id, float x, float y) {
		Point p;
		try {
			em.getTransaction().begin();
			p = find(id);
			p.setX(x);
			p.setY(y);
			em.persist(p);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		return p;
	}

	public boolean pointInitialized(Object id) {
		PersistenceUtil util = Persistence.getPersistenceUtil();
		Point p = em.find(Point.class, id);
		if (p != null)
			return util.isLoaded(p, "x") && util.isLoaded(p, "y") && util.isLoaded(p, "thirdDimention");
		return false;
	}

	public void addPoints(int amount) {
		for (int i = 0; i < amount; i++) {
			create(i, i * i);
		}
	}

	public void printPointsWithLineInfo() {
		System.out.println(jpql.join());
	}

	public void printListOfIdWithLine() {
		for (Object o[] : jpql.findIdWithLine()) {
			long id = (Long) o[0];
			Line l = (Line) o[1];
			System.out.println(id + " " + l);
		}
	}

	public void printAll() {
		System.out.println(jpql.selectAll());
		// System.out.println(criteria.selectAll());
	}

	public void printByFigure(Figure figure) {
		System.out.println(jpql.selectFigure(figure));
		// System.out.println(criteria.selectFigure(figure));
	}

	public long getAmount() {
		return jpql.countAll();
		// return criteria.selectAll();
	}

	public void printByFilter(Figure f, Float x, Float y, Float z) {
		System.out.println(jpql.filterPoints(f, x, y, z));
		// System.out.println(criteria.filterPoints(f, x, y, z));
	}
}
