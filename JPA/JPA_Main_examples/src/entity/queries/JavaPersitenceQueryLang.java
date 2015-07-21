package entity.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Figure;
import entity.Point;

public class JavaPersitenceQueryLang implements Basics {

	private EntityManager em;

	public JavaPersitenceQueryLang(EntityManager em) {
		this.em = em;
	}

	public List<Point> selectAll() {
		TypedQuery<Point> typedQuery = em.createQuery("SELECT p FROM Point p", Point.class);
		return typedQuery.getResultList();
	}

	public List<Point> selectFigure(Figure figure) {
		TypedQuery<Point> tq = em.createQuery("Select p from Point p where p.thirdDimention.figure = :figureType",
				Point.class);
		return tq.setParameter("figureType", figure).getResultList();
	}

	public long countAll() {
		TypedQuery<Long> tq = em.createQuery("Select count(p) from Point p", Long.class);
		return tq.getSingleResult();
	}

	public List<Point> filterPoints(Figure f, Float x, Float y, Float z) {
		StringBuffer query = new StringBuffer();
		query.append("select p ");
		query.append("from Point p ");

		query.append("where ");
		List<String> params = new ArrayList<String>();
		if (f != null)
			params.add("p.thirdDimention.figure = :figure");
		if (x != null)
			params.add("p.x = :x");
		if (y != null)
			params.add("p.y = :y");
		if (z != null)
			params.add("p.thirdDimention.z = :z");
		if (params.size() == 0)
			throw new RuntimeException("Set some parameters for query");

		for (int i = 0; i < params.size(); i++) {
			if (i > 0)
				query.append(" and ");
			query.append(params.get(i));
		}

		TypedQuery<Point> tq = em.createQuery(query.toString(), Point.class);
		if (f != null)
			tq.setParameter("figure", f);
		if (x != null)
			tq.setParameter("x", x);
		if (y != null)
			tq.setParameter("y", y);
		if (z != null)
			tq.setParameter("z", z);

		return tq.getResultList();
	}

}
