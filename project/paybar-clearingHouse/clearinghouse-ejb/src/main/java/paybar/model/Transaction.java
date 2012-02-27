package paybar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "getTransactionsByUserName", query = "Select tr FROM  DetailAccount da, Transaction tr WHERE da.userName = ?1 AND tr.detailAccount = da"),
		@NamedQuery(name = "getTransactionsByCompanyName", query = "Select tr FROM Transaction tr, Partner p, IN(p.pointsOfSale) ps WHERE p.userName = ?1 AND tr.pos = ps") })
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;

	double amount;

	@Temporal(TemporalType.TIMESTAMP)
	Date transactionTime;

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

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

}
