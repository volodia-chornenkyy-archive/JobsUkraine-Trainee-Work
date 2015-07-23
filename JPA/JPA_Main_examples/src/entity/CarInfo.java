package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CarInfo {

	private long id;

	private String number;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public CarInfo() {
		// TODO Auto-generated constructor stub
	}

	public CarInfo(long id, String number) {
		super();
		this.id = id;
		this.number = number;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaProvider");
		EntityManager em = emf.createEntityManager();

		TypedQuery<CarInfo> tq = em.createQuery("select new entity.CarInfo(c.id,cn.number) from Car c, CarNumber cn where c.number=cn.id",
				CarInfo.class);
		System.out.println(tq.getResultList());
	}

	@Override
	public String toString() {
		return "CarInfo [id=" + id + ", number=" + number + "]\n";
	}
	
	

}
