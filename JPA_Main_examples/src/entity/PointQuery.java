package entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

// Query interface should be used mainly when the query result type is unknown
// When a more specific result type is expected queries should usually use the TypedQuery

public class PointQuery {

	private EntityManager em;

	public PointQuery(EntityManager em) {
		this.em = em;
	}

	public List selectAllQuery() {
		Query simpleQuery = em.createQuery("Select p from Point p");
		return simpleQuery.getResultList();
	}

	public List<Point> selectAllTypedQuery() {
		TypedQuery<Point> typedQuery = em.createQuery("SELECT p FROM Point p", Point.class);
		return typedQuery.getResultList();
	}

	public List<Point> selectAllCriteriaQuery() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> r = cq.from(Point.class);
		cq.select(r);

		TypedQuery<Point> tq = em.createQuery(cq);
		return tq.getResultList();
	}

	public List<Point> selectFigureTypedQuery(Figure figureType) {
		TypedQuery<Point> tq = em.createQuery("Select p from Point p where p.thirdDimention.figure = :figureType",
				Point.class);
		return tq.setParameter("figureType", figureType).getResultList();
	}

	public List<Point> selectFigureCriteriaQuery(Figure figureType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Point> cq = cb.createQuery(Point.class);
		Root<Point> r = cq.from(Point.class);
		
		ParameterExpression<Figure> pe = cb.parameter(Figure.class);
		cq.select(r).where(cb.equal(r.get("thirdDimention.figure"), pe));

		TypedQuery<Point> tq = em.createQuery(cq);
		tq.setParameter(pe, figureType);
		return tq.getResultList();
	}

}
