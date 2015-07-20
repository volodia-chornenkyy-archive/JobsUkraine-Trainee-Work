package model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import servlets.PointTastFacade;

@Stateless
public class PointServies implements PointTastFacade {

	@PersistenceContext
	private EntityManager em;

	
	@PostConstruct
	public void init() {
	    if (this.em == null) {
	        System.err.println("EntityManages is NULL");
	    }
	}

	
	public void addMovie(Point point) {
		try {
			em.persist(point);
		} catch (NullPointerException e) {
			System.out.println("EntityMeneger == null");
		}
	}

	public void deleteMovie(Point point) throws Exception {
		em.remove(point);
	}

	public List<Point> getPoints() throws Exception {
		TypedQuery<Point> query = em.createQuery("SELECT p from Point as p", Point.class);
		return query.getResultList();
	}
}
