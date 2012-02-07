package paybar.wsmodel;

import java.io.Serializable;
import java.util.Date;

import paybar.model.Coupon;

public class WsCoupon implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String locationHash;
	
	private Date validFrom;

	private Date validUntil;

	private Date usedDate;
	
	private boolean banned;

	private String couponCode;

	public WsCoupon(Coupon c) {
		super();
		this.locationHash = c.getLocationHash();
		this.validFrom = c.getValidFrom();
		this.validUntil = c.getValidUntil();
		this.usedDate = c.getUsedDate();
		this.banned = c.isBanned();
		this.couponCode = c.getCouponCode();
	}

	public WsCoupon() {
		
	}
	
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

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}
