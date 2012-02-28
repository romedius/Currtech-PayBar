package at.ac.uibk.paybar.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This Class is even Used for Transfer of the Coupons.
 * */
public class FastCoupon implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String locationHash;
	
	private Date validFrom;

	private Date validUntil;

	private Date usedDate;
	
	private String couponCode;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocationHash() {
		return locationHash;
	}

	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}
