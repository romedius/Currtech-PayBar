package paybar.data;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import paybar.model.Coupon;

@Stateless
public class CouponResource {
	
	 @PersistenceContext(name = "primary")
	private EntityManager em;
	
	/**
	 * creates a new Coupon
	 * 
	 * @param banned
	 * @param couponCode
	 * @param locationHash
	 * @param usedDate
	 * @param validFrom
	 * @param validUntil
	 */
	public void createNewCoupon(boolean banned, String couponCode, String locationHash,
			Date usedDate, Date validFrom, Date validUntil){
		Coupon newCoupon = new Coupon();
		newCoupon.setBanned(banned);
		newCoupon.setCouponCode(couponCode);
		newCoupon.setLocationHash(locationHash);
		newCoupon.setUsedDate(usedDate);
		newCoupon.setValidFrom(validFrom);
		newCoupon.setValidUntil(validUntil);
		
		em.persist(newCoupon);
		em.flush();
	}
	
	/**
	 * isValidCoupon checks whether the couponCode is
	 * valid and can be used correctly.
	 * 
	 * @param couponCode 
	 * @return -1 code already used
	 * 			1 success
	 */
	@SuppressWarnings("unchecked")
	public int isValidCoupon(String couponCode){
		
		Query query = em.createQuery( "Select a from Coupon a where a.couponCode like :param" );
		query.setParameter( "param", couponCode );
		List<Coupon> list = query.getResultList();
		if (list !=  null && !list.isEmpty() && list.get(0) != null){
			Coupon c = list.get(0);
			System.out.println(c.toString());
			if (!c.isBanned()){
				return 1;
			}
		}
		
		return -1;
	}
}
