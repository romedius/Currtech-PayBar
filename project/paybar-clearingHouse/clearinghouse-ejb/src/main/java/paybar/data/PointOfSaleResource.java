package paybar.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import paybar.model.PointOfSale;

@Stateless
public class PointOfSaleResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	/**
	 * creates a new Account
	 */
	public void createNewPointOfSale(String locationhash, String name) {
		PointOfSale newPointOfSale = new PointOfSale();
		newPointOfSale.setLocationHash(locationhash);
		newPointOfSale.setName(name);
		em.persist(newPointOfSale);
		em.flush();
	}


}
