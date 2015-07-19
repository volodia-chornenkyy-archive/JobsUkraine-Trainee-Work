package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

// Query interface should be used mainly when the query result type is unknown
// When a more specific result type is expected queries should usually use the TypedQuery

public class PointQuery {

	private EntityManager em;

	public PointQuery(EntityManager em) {
		this.em = em;
	}

	public List selectAllPointsQuery() {
		Query simpleQuery = em.createQuery("Select p from Point p");
		return simpleQuery.getResultList();
	}

	public List<Point> selectAllPointsJPQL() {
		TypedQuery<Point> typedQuery = em.createQuery("SELECT p FROM Point p", Point.class);
		return typedQuery.getResultList();
	}

	public List<Point> selectAllPointsCriteria() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> r = cq.from(Point.class);
		cq.select(r);

		TypedQuery<Point> tq = em.createQuery(cq);
		return tq.getResultList();
	}

	public List<Point> selectFigureJPQL(Figure figureType) {
		TypedQuery<Point> tq = em.createQuery("Select p from Point p where p.thirdDimention.figure = :figureType",
				Point.class);
		return tq.setParameter("figureType", figureType).getResultList();
	}

	public List<Point> selectFigureCriteria(Figure figureType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> r = cq.from(Point.class);

		// don't forget set param value it typedQuery
		// ParameterExpression<Figure> pe = cb.parameter(Figure.class);
		// cq.select(r).where(cb.equal(r.get("thirdDimention.figure"), pe));
		// OR
		cq.select(r).where(cb.equal(r.get("thirdDimention.figure"), figureType));

		TypedQuery<Point> tq = em.createQuery(cq);
		// tq.setParameter(pe, figureType);
		return tq.getResultList();
	}

	public long countAllPointsJPQL() {
		TypedQuery<Long> tq = em.createQuery("Select count(p) from Point p", Long.class);
		return tq.getSingleResult();
	}

	public long countAllPointsCriteria() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Point.class)));

		TypedQuery<Long> tq = em.createQuery(cq);
		return tq.getSingleResult();
	}

	public List<Point> filterPointsJPQL(Figure f, Float x, Float y, Float z) {
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

	public List<Point> filterPointsCriteria(Figure f, Float x, Float y, Float z) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> p = cq.from(Point.class);
		cq.select(p);

		List<Predicate> params = new ArrayList<Predicate>();
		if (f != null) {
			ParameterExpression<Figure> pe = cb.parameter(Figure.class, "figure");
			params.add(cb.equal(p.get("thirdDimention").get("figure"), pe));
		}
		if (x != null) {
			ParameterExpression<Float> pe = cb.parameter(Float.class, "x");
			params.add(cb.equal(p.get("x"), pe));
		}
		if (y != null) {
			ParameterExpression<Float> pe = cb.parameter(Float.class, "y");
			params.add(cb.equal(p.get("y"), pe));
		}
		if (z != null) {
			ParameterExpression<Float> pe = cb.parameter(Float.class, "z");
			params.add(cb.equal(p.get("thirdDimention").get("z"), pe));
		}
		if (params.size() == 0)
			throw new RuntimeException("Set some parameters for query");
		else if (params.size() == 1)
			cq.where(params.get(0));
		else
			cq.where(cb.and(params.toArray(new Predicate[0]))); // ????

		TypedQuery<Point> tq = em.createQuery(cq);
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
