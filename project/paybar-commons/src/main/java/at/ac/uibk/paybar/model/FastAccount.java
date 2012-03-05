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

	private long version = Long.MIN_VALUE; // TODO: check for overflow

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

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
	
	public void increaseVersion() {
		this.version++;
	}

	/*
	public InequalVersionComparisonResult compareTo(EntryVersion arg0) {
		InequalVersionComparisonResult result = null;
		if (arg0 instanceof FastAccount) {
			FastAccount f2 = (FastAccount) arg0;
			long version2 = f2.getVersion();

			if (this.version < version2) {
				result = InequalVersionComparisonResult.AFTER;
			} else if (this.version == version2) {
				// check if every value is the same
				// ok, it seems to be the same... but is it?
				// TODO: check in partitioning modes for REAL equality.
				// not relevant for this prototype's mode
				result = InequalVersionComparisonResult.EQUAL;
			} else {
				result = InequalVersionComparisonResult.BEFORE;
			}
		}

		return result;
	}
	*/

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
