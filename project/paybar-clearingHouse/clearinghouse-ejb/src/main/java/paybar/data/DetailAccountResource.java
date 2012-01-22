package paybar.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import paybar.model.Account;
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
			String phoneNumber, Account account, boolean active, String locationhash) {
		DetailAccount newDetailAccount = new DetailAccount();
		newDetailAccount.setAccount(account);
		newDetailAccount.setAdress(adress);
		newDetailAccount.setFirstName(firstName);
		newDetailAccount.setPassword(password);
		newDetailAccount.setPhoneNumber(phoneNumber);
		newDetailAccount.setSureName(sureName);
		newDetailAccount.setUserName(userName);
		newDetailAccount.setActive(active);
		newDetailAccount.setLocationHash(locationhash);

		em.persist(newDetailAccount);
		em.flush();
	}


}
