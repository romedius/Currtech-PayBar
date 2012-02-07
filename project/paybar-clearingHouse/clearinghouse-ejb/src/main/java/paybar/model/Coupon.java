package paybar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "getCouponsByUserName", query = "Select c FROM  DetailAccount da, IN (da.coupons) c WHERE da.userName = ?1 ")
})
public class Coupon implements Serializable {
	
	public static final long VALID_TIME_OF_COUPON = 1209600000l;
	
	/**
	 * Minimal Number of coupons of a user before new ones will be generated.
	 * */
	public static final int MIN_NUM_OF_CUPONS = 5;

	/**
	 * Upon coupon generation, this is the number of coupons which will be generated.
	 * */
	public static final int GENERATE_NUM_OF_CUPONS = 10;
	
	
	public Coupon(String locationHash, Date validFrom, Date validUntil,
			Date usedDate, boolean banned, String couponCode) {
		super();
		this.locationHash = locationHash;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.usedDate = usedDate;
		this.banned = banned;
		this.couponCode = couponCode;
	}

	public Coupon() {
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@NotEmpty
	private String locationHash;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validUntil;

	@Temporal(TemporalType.TIMESTAMP)
	private Date usedDate;
	
	private boolean banned;

	@NotNull
	@NotEmpty
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
