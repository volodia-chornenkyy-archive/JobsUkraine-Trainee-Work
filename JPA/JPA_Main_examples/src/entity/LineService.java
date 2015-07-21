package entity;

import java.util.Collection;

import javax.persistence.EntityManager;

public class LineService {

	private EntityManager em;

	public LineService(EntityManager em) {
		this.em = em;
	}

	public Line create(String name) {
		Line line;
		try {
			em.getTransaction().begin();
			line = new Line(name);
			em.persist(line);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		return line;
	}

	public void addPoint(Object line_id, Object point_id) {
		em.getTransaction().begin();
		Line line = find(line_id);
		Point p = new PointService(em).find(point_id);
		p.setLine(line);
		em.persist(p);
		em.getTransaction().commit();
	}

	public Line find(Object id) {
		return em.find(Line.class, id);
	}
	
	public Collection<Point> getPoints(Object id){
		return find(id).getPoints();
	}

}
