package at.ac.uibk.paybar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This Class is even Used for Storage of Accounts. Do not forget to increase
 * version after doing your changes.
 * */
public class FastAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private long credit;

	private HashMap<String, FastCoupon> fastCoupons = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	public void addFastCoupons(ArrayList<FastCoupon> fastCoupons2) {
		if(this.fastCoupons == null) {
			this.fastCoupons = new HashMap<String, FastCoupon>();
		}
		for (FastCoupon fastCoupon : fastCoupons2) {
			this.fastCoupons.put(fastCoupon.getCouponCode(), fastCoupon);	
		}
	}

	public void removeCoupon(String tanCode) {
		if(fastCoupons != null) {
			fastCoupons.remove(tanCode);
		}
	}
}
