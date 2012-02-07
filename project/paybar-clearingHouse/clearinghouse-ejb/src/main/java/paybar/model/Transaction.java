package paybar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "getTransactionsByUserName", query = "Select tr FROM  DetailAccount da, Coupon c,Transaction tr WHERE da.userName = ?1 AND tr.coupon = c AND tr.detailAccount = da"),
		@NamedQuery(name = "getTransactionsByCompanyName", query = "Select tr FROM Transaction tr, Partner p WHERE p.userName = ?1 AND tr.pos in (p.pointsOfSale)") })
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	double amount;

	long transactionTime;

	@OneToOne
	private Coupon coupon;

	@OneToOne
	private PointOfSale pos;

	@OneToOne
	private DetailAccount detailAccount;

	public DetailAccount getDetailAccount() {
		return detailAccount;
	}

	public void setDetailAccount(DetailAccount detailAccount) {
		this.detailAccount = detailAccount;
	}

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

	public long getTransactionTime() {
		return transactionTime;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}

}
