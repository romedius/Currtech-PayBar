package paybar.wsmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import paybar.model.Coupon;
import paybar.model.DetailAccount;

public class WsDetailAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private boolean active;

	private String adress;

	private String firstName;

	private String sureName;

	private String password;

	private String PhoneNumber;

	private String userName;

	private String locationHash;

	private String moveToLocationHash;

	private long credit;

	private String securityKey;

	private List<Long> couponsId;

	public WsDetailAccount() {
	}

	public WsDetailAccount(DetailAccount da) {
		this.active = da.isActive();
		this.adress= da.getAdress();
		this.couponsId= new ArrayList<Long>();
		for (Coupon c : da.getCoupons()) {
			couponsId.add(c.getId());
		}
		this.credit= da.getCredit();
		this.firstName= da.getFirstName();
		this.id= da.getId();
		this.locationHash= da.getLocationHash();
		this.moveToLocationHash= da.getMoveToLocationHash();
		this.password= null;// The password is not given back in this way for Security reasons
		this.PhoneNumber= da.getPhoneNumber();
		this.securityKey= null;// The security key is not given back in this way for Security reasons
		this.sureName= da.getSureName();
		this.userName= da.getUserName();
	}

	public String getAdress() {
		return adress;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getId() {
		return id;
	}

	public String getLocationHash() {
		return locationHash;
	}

	public String getMoveToLocationHash() {
		return moveToLocationHash;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getSureName() {
		return sureName;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
	}

	public void setMoveToLocationHash(String moveToLocationHash) {
		this.moveToLocationHash = moveToLocationHash;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Long> getCoupons() {
		return couponsId;
	}

	public void setCoupons(List<Long> couponsId) {
		this.couponsId = couponsId;
	}

	public long getCredit() {
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

}
