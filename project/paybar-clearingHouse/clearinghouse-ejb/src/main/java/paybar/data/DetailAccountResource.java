package paybar.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import paybar.model.Coupon;
import paybar.model.DetailAccount;

@Stateless
public class DetailAccountResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	@Inject
	private CouponResource cr;

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
		regenerateCoupons(userName);
	}

	public void createNewDetailAccount(DetailAccount newDetailAccount) {
		em.persist(newDetailAccount);
		em.flush();
		regenerateCoupons(newDetailAccount.getUserName());
	}

	public DetailAccount getUserByName(String name, boolean eager)
			throws NoResultException, Exception {
		Query query = em.createNamedQuery("getUserByName");
		query.setParameter(1, name);
		DetailAccount result = (DetailAccount) query.getSingleResult();
		if (eager) {
			result.getCoupons().size();
		}
		return result;

	}

	public List<Coupon> getCouponListByUserName(String name)
			throws NoResultException, Exception {
		List<Coupon> coupons = new ArrayList<Coupon>();
		DetailAccount da = getUserByName(name, false);
		coupons.addAll(da.getCoupons());
		return coupons;
	}

	public void updateDetailAccount(DetailAccount detailAccount) {
		em.merge(detailAccount);
		em.flush();
	}

	/**
	 * This method recreates a new set of coupon codes for this user and adds
	 * the coupon codes to the old list of this account.
	 * 
	 * @return
	 */
	public void regenerateCoupons(String name) {
		Query query = em.createNamedQuery("getUserByName");
		query.setParameter(1, name);
		DetailAccount result = (DetailAccount) query.getSingleResult();
		List<Coupon> currentCoupons = result.getCoupons();
		if (currentCoupons == null) {
			currentCoupons = new ArrayList<Coupon>();
			result.setCoupons(currentCoupons);
		}
		long currentTime = System.currentTimeMillis();
		Date validFrom = new Date(currentTime);
		Date validUntil = new Date(currentTime + Coupon.VALID_TIME_OF_COUPON);
		Random r = new Random(currentTime);
		for (int i = currentCoupons.size(); i < Coupon.GENERATE_NUM_OF_CUPONS; i++) {
			Coupon coupon = new Coupon(result.getLocationHash(), validFrom,
					validUntil, null, false, result.getLocationHash()
							+ result.getId() + "." + i + "." + r.nextInt()); // TODO:
																				// code
																				// generation
																				// needs
																				// to
																				// be
																				// improved.
																				// Maybe
																				// calculate
																				// some
																				// time
																				// +
																				// index
																				// hash
																				// out
																				// of
																				// the
																				// user
																				// id
			cr.createNewCoupon(coupon);
			currentCoupons.add(coupon);
		}
		em.merge(result);
		em.flush();

	}

	public void invalidateDevices(String username)
	{
		Query query = em.createNamedQuery("getUserByName");
		query.setParameter(1, username);
		DetailAccount result = (DetailAccount) query.getSingleResult();
		result.getCoupons().clear();
		result.setSecurityKey("");
		regenerateCoupons(username);	
	}

}
