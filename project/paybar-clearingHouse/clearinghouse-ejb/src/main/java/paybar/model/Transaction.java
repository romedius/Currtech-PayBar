package paybar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@NamedQueries({
	  @NamedQuery(name = "getTransactionsByUserName", query = "Select tr FROM Transaction tr, DetailAccount da WHERE da.userName = ?1 AND tr.coupon in (da.coupons)"),	  
	  @NamedQuery(name = "getTransactionsByCompanyName", query = "Select tr FROM Transaction tr, Partner p WHERE p.userName = ?1 AND tr.pos in (p.pointsOfSale)")	  
})
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	double amount;

	@NotNull
	@NotEmpty
	String locationHash;

	long transactionTime;

	@OneToOne
	private Coupon coupon;

	@OneToOne
	private PointOfSale pos;

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public PointOfSale getPos() {
		return pos;
	}

	public void setPos(PointOfSale pos) {
		this.pos = pos;
	}

	public double getAmount() {
		return amount;
	}

	public Long getId() {
		return id;
	}

	public String getLocationHash() {
		return locationHash;
	}

	public long getTransactionTime() {
		return transactionTime;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
	}

	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}

}
