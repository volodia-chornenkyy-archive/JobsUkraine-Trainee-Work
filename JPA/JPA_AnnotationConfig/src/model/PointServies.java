package model;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

@Stateless
public class PointServies {

	@PersistenceContext(name="pointProvider",type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public void addMovie(Point point) throws Exception {
		em.persist(point);
	}

	public void deleteMovie(Point point) throws Exception {
		em.remove(point);
	}

	public List<Point> getPoints() throws Exception {
		TypedQuery<Point> query = em.createQuery("SELECT p from Point as p", Point.class);
		return query.getResultList();
	}
}
