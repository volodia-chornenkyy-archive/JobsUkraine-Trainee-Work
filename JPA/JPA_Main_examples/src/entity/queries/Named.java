package entity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Figure;
import entity.Point;

public class Named {

	private EntityManager em;

	public Named(EntityManager em) {
		this.em = em;
	}

	public List<Point> selectAllPoints() {
		TypedQuery<Point> tq = em.createNamedQuery("Point.getAll", Point.class);
		return tq.getResultList();
	}

	public List<Point> selectFigure(Figure figure) {
		TypedQuery<Point> tq2 = em.createNamedQuery("Point.selectFigure", Point.class);
		return tq2.setParameter("figure", figure).getResultList();
	}

	public List<Point> selectLessThanId(long maxId) {
		TypedQuery<Point> tq3 = em.createNamedQuery("Point.selectLess", Point.class);
		return tq3.setParameter("maxValue", maxId).getResultList();
	}

}
