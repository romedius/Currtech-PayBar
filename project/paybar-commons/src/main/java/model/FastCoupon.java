package model;

import java.io.Serializable;
import java.util.Date;
public class FastCoupon implements Serializable {

	public FastAccount getFastAccount() {
		return fastAccount;
	}

	public void setFastAccount(FastAccount fastAccount) {
		this.fastAccount = fastAccount;
	}

	private static final long serialVersionUID = 1L;

	private long id;

	private String locationHash;
	
	private Date validFrom;

	private Date validUntil;

	private Date usedDate;
	
	private String couponCode;
	
	private FastAccount fastAccount;

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
