package paybar.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import paybar.model.DetailAccount;

@Stateless
public class DetailAccountResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	/**
	 * creates a new Account
	 */
	public void createNewDetailAccount(String sureName, String firstName,
			String userName, String password, String adress,
			String phoneNumber, boolean active, String locationhash,
			long credit, String securityKey) {
		DetailAccount newDetailAccount = new DetailAccount();
		newDetailAccount.setAdress(adress);
		newDetailAccount.setFirstName(firstName);
		newDetailAccount.setPassword(password);
		newDetailAccount.setPhoneNumber(phoneNumber);
		newDetailAccount.setSureName(sureName);
		newDetailAccount.setUserName(userName);
		newDetailAccount.setActive(active);
		newDetailAccount.setLocationHash(locationhash);
		newDetailAccount.setCredit(credit);
		newDetailAccount.setSecurityKey(securityKey);
		em.persist(newDetailAccount);
		em.flush();
	}

	public void createNewDetailAccount(DetailAccount newDetailAccount) {
		em.persist(newDetailAccount);
		em.flush();
	}

	public DetailAccount getUserByName(String name) {
		try {
			Query query = em.createNamedQuery("getUserByName");
			query.setParameter(1, name);
			DetailAccount result = (DetailAccount) query.getSingleResult();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
