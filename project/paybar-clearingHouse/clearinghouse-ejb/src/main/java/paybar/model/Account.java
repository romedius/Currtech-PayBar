package paybar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	/**
	 * Credit of an user in cents.
	 * */
	private long credit;

	/**
	 * This Value is the Security Key used by the mobile Apps to identify
	 * themselves towards the Server to avoid a re-login.
	 * */
	@NotNull
	@NotEmpty
	private String securityKey;

	/**
	 * This is the list of coupons of a user.
	 * */
	@OneToMany
	private List<Coupon> coupons;
	
	/**
	 * This is the list of used or otherwise invalidated coupons of the user.
	 */
	@OneToMany
	private List<Coupon> oldCoupons;

	public List<Coupon> getOldCoupons() {
		return oldCoupons;
	}

	public void setOldCoupons(List<Coupon> oldCoupons) {
		this.oldCoupons = oldCoupons;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	/**
	 * This method recreates a new set of coupon codes for this user and adds
	 * the coupon codes to the old list of this account.
	 * 
	 * @return
	 */
	public List<Coupon> regenerateCoupons(String locationHash, String userId) {
		List<Coupon> currentCoupons = this.getCoupons();
		
		if(currentCoupons != null && currentCoupons.size() > 0) {
			List<Coupon> olderCoupons = this.getOldCoupons();
			if(olderCoupons == null) {
				olderCoupons = new ArrayList(currentCoupons.size());
			}
			
			olderCoupons.addAll(currentCoupons); // TODO: maybe we need here some unique checks
			currentCoupons = new ArrayList<Coupon>(10);
			long currentTime = System.currentTimeMillis();
			Date validFrom = new Date(currentTime);
			Date validUntil = new Date(currentTime + Coupon.VALID_TIME_OF_COUPON);
			for(int i=0; i < 10; i++) {
				Coupon coupon = new Coupon(locationHash, validFrom, validUntil, null, false, locationHash + userId + "." + i + "." /*11 stellen*/);
				
			}
		}
		
		return null;
	}

}
