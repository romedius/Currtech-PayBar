package paybar.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import paybar.model.Account;
import paybar.model.Coupon;

@Stateless
public class AccountResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	/**
	 * creates a new Account
	 */
	public void createNewAccount(String locationHash, long credit, boolean active,
			String securityKey, List<Coupon> coupons) {
		Account newAccount = new Account();
		newAccount.setCoupons(coupons);
		newAccount.setCredit(credit);
		newAccount.setSecurityKey(securityKey);
		em.persist(newAccount);
		em.flush();
	}


}
