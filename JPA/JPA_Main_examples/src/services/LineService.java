package services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import entity.Line;
import entity.Point;
import entity.queries.JavaPersitenceQueryLang;

public class LineService {

	private EntityManager em;
	private JavaPersitenceQueryLang jpql;

	public LineService(EntityManager em) {
		this.em = em;
		jpql = new JavaPersitenceQueryLang(em);
	}

	public Line create(String name, Object from_id, Object to_id) {
		Line line;
		try {
			em.getTransaction().begin();
			line = new Line(name);
			em.persist(line);
			em.getTransaction().commit();

			for (long i = (Long) from_id; i <= (Long) to_id; i++) {
				addPoint(line.getId(), i);
			}

		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		return line;
	}

	public void addPoint(Object line_id, Object point_id) {
		em.getTransaction().begin();
		Line line = find((Long) line_id);
		Point p = new PointService(em).find((Long) point_id);
		p.setLine(line);
		em.persist(p);
		em.getTransaction().commit();
	}

	public Line find(Object id) {
		return em.find(Line.class, id);
	}

	public Collection<Point> getPoints(String name) {
		List<Line> lines = jpql.findLine(name);
		return lines.get(lines.size()-1).getPoints();
	}

	public Collection<Point> getPoints(Object id) {
		return find(id).getPoints();
	}

}
