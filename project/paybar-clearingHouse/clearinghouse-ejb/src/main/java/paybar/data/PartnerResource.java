package paybar.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import paybar.model.Partner;
import paybar.model.PointOfSale;

@Stateless
public class PartnerResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	/**
	 * creates a new Account
	 */
	public void createNewpartner(String locationHash, String adress,
			String billingInformation, String userName, String password,
			List<PointOfSale> pointsOfSale, long credit) {
		Partner newPartner = new Partner();
		newPartner.setAdress(adress);
		newPartner.setBillingInformation(billingInformation);
		newPartner.setCredit(credit);
		newPartner.setLocationHash(locationHash);
		newPartner.setPassword(password);
		newPartner.setPointsOfSale(pointsOfSale);
		newPartner.setUserName(userName);

		em.persist(newPartner);
		em.flush();
	}

	public void createNewpartner(Partner newPartner) {
		em.persist(newPartner);
		em.flush();
	}
	

	public Partner getPartnerByName(String name, boolean eager)
			throws NoResultException, Exception {
		Query query = em.createNamedQuery("getPartnerByName");
		query.setParameter(1, name);
		Partner result = (Partner) query.getSingleResult();
		if (eager) {
			result.getPointsOfSale().size();
		}
		return result;
	}

}
