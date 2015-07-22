package services;

import javax.persistence.EntityManager;

import entity.Phone;
import entity.queries.JavaPersitenceQueryLang;

public class PhoneService {

	private EntityManager em;
	private JavaPersitenceQueryLang jpql;

	public PhoneService(EntityManager em) {
		this.em = em;
		jpql = new JavaPersitenceQueryLang(em);
	}

	public Phone create(String phoneNo, String phoneType) {
		try {
			em.getTransaction().begin();
			Phone p = new Phone();
			p.setNumber(phoneNo);
			p.setType(phoneType);
			em.persist(p);
			em.getTransaction().commit();
			return p;
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
	}

}
