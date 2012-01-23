package paybar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
	  @NamedQuery(name = "getUserByName", query = "Select da FROM DetailAccount da WHERE da.userName = ?1")	  
})
public class DetailAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	/**
	 * Boolean Value to block a user. Blocked useres will not be loaded into the
	 * fastcheck-infinispan instances.
	 * */
	private boolean active;

	private String adress;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String sureName;

	@NotNull
	@NotEmpty
	@Size(min = 6, max = 25)
	private String password;

	private String PhoneNumber;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	private String userName;

	/**
	 * This Value is used for the Infinispan cache to Attach an account to a
	 * certain infinispan Group
	 * */
	@NotNull
	@NotEmpty
	private String locationHash;

	private String moveToLocationHash;

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
	public void regenerateCoupons() {
		List<Coupon> currentCoupons = this.getCoupons();
		if (currentCoupons == null) {
			currentCoupons = new ArrayList<Coupon>();
			this.setCoupons(currentCoupons);
		}
		long currentTime = System.currentTimeMillis();
		Date validFrom = new Date(currentTime);
		Date validUntil = new Date(currentTime + Coupon.VALID_TIME_OF_COUPON);
		Random r = new Random(currentTime);
		for (int i = currentCoupons.size(); i < Coupon.GENERATE_NUM_OF_CUPONS; i++) {
			Coupon coupon = new Coupon(this.locationHash, validFrom,
					validUntil, null, false, this.locationHash + this.id + "."
							+ i + "." + r.nextInt());
			currentCoupons.add(coupon);
		}
	}

}
