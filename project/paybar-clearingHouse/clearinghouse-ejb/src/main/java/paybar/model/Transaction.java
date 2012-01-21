package paybar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Transaction {

	@Id
	@GeneratedValue
	Long id;

	double amount;

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
