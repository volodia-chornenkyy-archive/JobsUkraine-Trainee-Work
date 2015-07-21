package entity.queries;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Figure;
import entity.Point;

public class CriteriaApi implements Basics {

	private EntityManager em;

	public CriteriaApi(EntityManager em) {
		this.em = em;
	}

	public List<Point> selectAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> r = cq.from(Point.class);
		cq.select(r);

		TypedQuery<Point> tq = em.createQuery(cq);
		return tq.getResultList();

	}

	public List<Point> selectFigure(Figure figure) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> r = cq.from(Point.class);

		// don't forget set param value it typedQuery
		// ParameterExpression<Figure> pe = cb.parameter(Figure.class);
		// cq.select(r).where(cb.equal(r.get("thirdDimention.figure"), pe));
		// OR
		cq.select(r).where(cb.equal(r.get("thirdDimention.figure"), figure));

		TypedQuery<Point> tq = em.createQuery(cq);
		// tq.setParameter(pe, figure);
		return tq.getResultList();

	}

	public long countAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Point.class)));

		TypedQuery<Long> tq = em.createQuery(cq);
		return tq.getSingleResult();
	}

	public List<Point> filterPoints(Figure f, Float x, Float y, Float z) {
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
